package com.deybson.sisDeCarros.domain.service;

import java.util.List;
import java.util.Optional;

import com.deybson.sisDeCarros.domain.model.Car;

public interface CarService {
	
	Optional<List<Car>> findAll();
	
	Optional<Car> findById(Long id);
	
	Car save(Car car);

}
