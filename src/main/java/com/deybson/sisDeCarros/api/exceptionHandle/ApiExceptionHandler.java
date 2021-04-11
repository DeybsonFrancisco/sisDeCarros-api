package com.deybson.sisDeCarros.api.exceptionHandle;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.deybson.sisDeCarros.domain.exception.CarException;
import com.deybson.sisDeCarros.domain.exception.UserException;
import com.deybson.sisDeCarros.domain.exception.model.Problema;
import com.deybson.sisDeCarros.domain.exception.model.Problema.Campo;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ArrayList<Campo> campos = new ArrayList<Campo>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			campos.add(new Campo(nome, mensagem));
		}

		var problema = new Problema();
		problema.setStatus(400);
		problema.setTitulo("one or more fields are missing");
		problema.setDatahora(LocalDateTime.now());
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<Object> userExceptionHandler(UserException ex, WebRequest request, HttpStatus status) {

		var problema = new Problema();
		problema.setTitulo(ex.getMessage());
		problema.setStatus(status.value());
		problema.setDatahora(LocalDateTime.now());

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(CarException.class)
	public ResponseEntity<Object> carExceptionHandler(CarException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		var problema = new Problema();
		problema.setTitulo(ex.getMessage());
		problema.setStatus(status.value());
		problema.setDatahora(LocalDateTime.now());

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

}
