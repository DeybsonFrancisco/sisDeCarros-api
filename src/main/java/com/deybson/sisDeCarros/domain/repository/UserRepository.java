package com.deybson.sisDeCarros.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deybson.sisDeCarros.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByLogin(String name);
	
	Optional<User> findByEmail(String email);
}

