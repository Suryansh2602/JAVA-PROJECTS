package com.info.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(UnAuthorisedException.class)
	public ResponseEntity<String> handleUnAuthorisedException(UnAuthorisedException e){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
public ResponseEntity<String> handleInternalServerError(Exception e){
	return ResponseEntity.internalServerError().body("Something Went Wrong");
}
}
