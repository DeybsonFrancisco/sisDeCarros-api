package com.deybson.sisDeCarros.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deybson.sisDeCarros.domain.model.User;

public interface UserService {
	
	Optional<Page<User>> findAll(Pageable pageable);
	
	Optional<User> findById(Long id);

	User save(User usuario);
	
	User update(Long id, User usuarios);
	
	void remove(Long id);
	
	User findByLogin(String login);
	
	

}
