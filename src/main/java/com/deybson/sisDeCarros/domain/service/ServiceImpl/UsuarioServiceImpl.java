package com.deybson.sisDeCarros.domain.service.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deybson.sisDeCarros.domain.exception.UserException;
import com.deybson.sisDeCarros.domain.model.User;
import com.deybson.sisDeCarros.domain.repository.UserRepository;
import com.deybson.sisDeCarros.domain.service.UserService;

@Service
public class UsuarioServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public Optional<Page<User>> findAll(Pageable pageable) {
		return Optional.of(repository.findAll(pageable));
	}

	@Override
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public User save(User user) {
		if (repository.findByLogin(user.getLogin()).isPresent()) {
			throw new UserException("Login already exist");
		}if(repository.findByEmail(user.getEmail()).isPresent()) {
			throw new UserException("Email already exist");
		}
			return repository.save(user);
	}

	@Override
	public User update(Long id, User user) {
		if (repository.existsById(id))
			return repository.save(user);

		throw new UserException("User not exist");
	}

	@Override
	public void remove(Long id) {
		if (!repository.existsById(id))
			repository.deleteById(id);

		throw new UserException("User not exist");
	}

}
