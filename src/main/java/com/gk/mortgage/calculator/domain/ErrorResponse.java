package com.gk.mortgage.calculator.domain;

import lombok.Getter;

public class ErrorResponse {
	
    public ErrorResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	@Getter
	private String code;
    
    @Getter
    private String message;
}
