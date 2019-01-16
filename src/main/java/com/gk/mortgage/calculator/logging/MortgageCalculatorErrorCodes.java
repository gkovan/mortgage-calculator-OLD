package com.gk.mortgage.calculator.logging;


public enum MortgageCalculatorErrorCodes {
	
	APPERR0001("Internal Server Error."),
	APPERR0002("Bad Input Request.");
	
	private String value;

	MortgageCalculatorErrorCodes(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
