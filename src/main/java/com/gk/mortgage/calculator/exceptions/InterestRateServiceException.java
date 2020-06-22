package com.gk.mortgage.calculator.exceptions;

import lombok.Getter;

public class InterestRateServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3815725537109044112L;
	
	@Getter
	private String errorCode;
	
	public InterestRateServiceException(final String errorCode, final String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
}
