package com.deybson.sisDeCarros.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deybson.sisDeCarros.api.model.CarInput;
import com.deybson.sisDeCarros.api.model.CarRepresantation;
import com.deybson.sisDeCarros.domain.model.Car;
import com.deybson.sisDeCarros.domain.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {

	@Autowired
	private CarService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<CarRepresantation>> findAll() {
		List<CarRepresantation> listModel = service.findAll().get().stream().map(car -> toModel(car))
				.collect(Collectors.toList());

		return ResponseEntity.status(200).body(listModel);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarRepresantation> findById(@PathVariable Long id){
		CarRepresantation carModel = toModel(service.findById(id).get());
		return ResponseEntity.status(200).body(carModel);
	}
	
	@PostMapping
	public ResponseEntity<CarRepresantation> save(@Valid @RequestBody CarInput carInput ){
		Car car = toEntity(carInput);
		CarRepresantation carModel = toModel(service.save(car));
		return ResponseEntity.status(201).body(carModel);
	}

	private CarRepresantation toModel(Car car) {
		return modelMapper.map(car, CarRepresantation.class);
	}
	
	private Car toEntity(CarInput carInput) {
		return modelMapper.map(carInput, Car.class);
	}
}
