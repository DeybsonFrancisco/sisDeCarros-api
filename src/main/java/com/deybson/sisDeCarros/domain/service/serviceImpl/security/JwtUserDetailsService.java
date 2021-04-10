package com.deybson.sisDeCarros.domain.service.serviceImpl.security;


import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deybson.sisDeCarros.domain.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		com.deybson.sisDeCarros.domain.model.User userData = service.findByLogin(login);
		return new User(userData.getLogin(), userData.getPassword(), new HashSet<>());
	}
}
