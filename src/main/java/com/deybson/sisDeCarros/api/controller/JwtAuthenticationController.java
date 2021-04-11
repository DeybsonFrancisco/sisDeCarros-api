package com.deybson.sisDeCarros.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deybson.sisDeCarros.config.JwtTokenUtil;
import com.deybson.sisDeCarros.config.jwt.model.JwtRequest;
import com.deybson.sisDeCarros.config.jwt.model.JwtResponse;
import com.deybson.sisDeCarros.domain.service.serviceImpl.security.JwtUserDetailsService;


@RestController
@CrossOrigin
@RequestMapping("/api/sing")
public class JwtAuthenticationController {

@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private JwtTokenUtil jwtTokenUtil;

@Autowired
private JwtUserDetailsService service;

@PostMapping
public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
	
	authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());
	
	final UserDetails userDetails = service
			.loadUserByUsername(authenticationRequest.getLogin());
	
	final String token = jwtTokenUtil.generateToken(userDetails);
	
return ResponseEntity.status(200).body(new JwtResponse(token));
}

private void authenticate(String username, String password) throws Exception {
try {
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
} catch (DisabledException e) {
	throw new Exception("USER_DISABLED", e);
} catch (BadCredentialsException e) {
	throw new Exception("INVALID_CREDENTIALS", e);
}
}
}