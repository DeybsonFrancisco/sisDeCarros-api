package com.deybson.sisDeCarros.domain.service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deybson.sisDeCarros.domain.exception.CarException;
import com.deybson.sisDeCarros.domain.model.Car;
import com.deybson.sisDeCarros.domain.repository.CarRepository;
import com.deybson.sisDeCarros.domain.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository repository;

	@Override
	public Optional<List<Car>> findAll() {
		return Optional.of(repository.findAll());
	}

	@Override
	public Optional<Car> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Car save(Car car) {
		if (!repository.existsBylicensePlate(car.getLicensePlate())) {
		Car novocarro = car;
			return repository.save(novocarro);
		}
		throw new CarException("Already exist a car whit this licensePlate");

	}

}
