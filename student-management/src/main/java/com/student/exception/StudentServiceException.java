package com.student.exception;

import lombok.Data;

@Data
public class StudentServiceException extends Exception {
	
	private String errorCode;
	private String errorMessage;
	public StudentServiceException(String errorCode, String errorMessage) {
		
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
