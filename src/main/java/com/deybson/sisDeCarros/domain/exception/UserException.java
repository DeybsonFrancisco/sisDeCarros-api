package com.deybson.sisDeCarros.domain.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	

	public UserException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	
	

}
