package com.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler (StudentServiceException.class)
	public ResponseEntity <String>  invalidPatientException(StudentServiceException ex){
		
		return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
	}
}
