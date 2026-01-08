package com.info.urbaneats.restaurant.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleInternalServerError(Exception e) {
		e.printStackTrace();
		return ResponseEntity.internalServerError().body("Oops! something went wrong");
	}

}
