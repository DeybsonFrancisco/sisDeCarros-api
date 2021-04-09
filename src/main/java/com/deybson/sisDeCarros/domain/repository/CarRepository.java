package com.deybson.sisDeCarros.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deybson.sisDeCarros.domain.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

	Optional<Car> findBylicensePlate(String licensePlate);
	boolean existsBylicensePlate(String licensePlate);
}
