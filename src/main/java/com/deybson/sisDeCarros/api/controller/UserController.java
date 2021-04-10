package com.deybson.sisDeCarros.api.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deybson.sisDeCarros.api.model.UserInput;
import com.deybson.sisDeCarros.api.model.UserRepresantation;
import com.deybson.sisDeCarros.domain.model.User;
import com.deybson.sisDeCarros.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<Page<UserRepresantation>> findAll(
			@RequestParam(name = "lines", defaultValue = "10") Integer lines,
			@RequestParam(name = "page", defaultValue = "0") Integer pages,
			@RequestParam(name = "dir", defaultValue = "ASC")String direction,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy){
		Pageable pageable = PageRequest.of(pages, lines, Direction.valueOf(direction), orderBy);
		Page<User> list = service.findAll(pageable).get();
		Page<UserRepresantation> listModel = new PageImpl<>(list.stream()
																.map(user -> toModel(user))
																.collect(Collectors.toList()));
		return ResponseEntity.status(200).body(listModel);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		Optional<User> user = service.findById(id);
		if(user.isPresent())
			return ResponseEntity.status(200).body(service.findById(id).get());
		
		return ResponseEntity.status(404).build();
	}
	
	@PostMapping
	public ResponseEntity<UserRepresantation> save(@Valid @RequestBody UserInput userInput){
		User user = toEntity(userInput);
		UserRepresantation userRepresantation = toModel(service.save(user));
		return ResponseEntity.status(201).body(userRepresantation);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserRepresantation> update(@PathVariable Long id, @Valid @RequestBody UserInput userInput){
		User user = toEntity(userInput);
		UserRepresantation userRepresantation = toModel(service.update(id, user));
		return ResponseEntity.status(200).body(userRepresantation);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id){
		service.remove(id);
		return ResponseEntity.status(403).build();
	}
	
	private User toEntity(UserInput userInput) {
		return modelMapper.map(userInput, User.class);
	}

	private UserRepresantation toModel(User user) {
		return modelMapper.map(user, UserRepresantation.class);
	}
}
