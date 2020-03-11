package com.gk.mortgage.calculator.exceptions;

import lombok.Getter;

public class BadRequestInputException extends RuntimeException{
	
	private static final long serialVersionUID = 2862747731675746497L;
	
	@Getter
	private String errorCode;

	public BadRequestInputException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BadRequestInputException(final String errorCode, final String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BadRequestInputException(final String message) {
		super(message);
	}
}
