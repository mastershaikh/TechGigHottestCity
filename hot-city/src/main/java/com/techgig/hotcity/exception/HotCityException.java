package com.techgig.hotcity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class HotCityException {

	@ExceptionHandler(CityNotFoundException.class)
	public static ResponseEntity<String> notFound(final String message, final HttpStatus status) {
		return new ResponseEntity<>(message, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException() {
		return new ResponseEntity<>("Error occured!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
