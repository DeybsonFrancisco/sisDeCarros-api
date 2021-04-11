package com.deybson.sisDeCarros.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deybson.sisDeCarros.api.model.UserRepresantation;
import com.deybson.sisDeCarros.config.JwtTokenUtil;
import com.deybson.sisDeCarros.domain.model.User;
import com.deybson.sisDeCarros.domain.service.UserService;

@RestController
@RequestMapping("/api/me")
public class MeController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<UserRepresantation> showDetailsActiveUser(HttpServletRequest req){
		
		String Authorization = req.getHeader("Authorization");
		String login = jwtUtil.getUsernameFromToken(Authorization.substring(7));
		
		User user = service.findByLogin(login);
		return ResponseEntity.status(200).body(toModel(user));	
		
	}
	private UserRepresantation toModel(User user) {
		return modelMapper.map(user, UserRepresantation.class);
	}

}
