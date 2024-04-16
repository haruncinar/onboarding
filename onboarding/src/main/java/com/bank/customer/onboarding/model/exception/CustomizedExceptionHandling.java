package com.bank.customer.onboarding.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		response.setCode(HttpStatus.NOT_FOUND.value());
		response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Object> handleLoginFailException(LoginException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		response.setCode(HttpStatus.EXPECTATION_FAILED.value());
		response.setError(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(UserAgeNotAllowedException.class)
	public ResponseEntity<Object> handleUserAgeNotAllowedException(UserAgeNotAllowedException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		response.setCode(HttpStatus.EXPECTATION_FAILED.value());
		response.setError(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<Object> handleUserExistException(UserExistException exception) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		response.setCode(HttpStatus.EXPECTATION_FAILED.value());
		response.setError(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}

}
