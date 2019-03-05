package com.gk.mortgage.calculator.domain;

import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;

import lombok.Data;

@Data
public class MortgageCalculatorRequest {
	@PII
	Double principal;
	Double interestRate;
	Integer term;
	String type;
	@PCI
	String creditCard = "4111111111111111";
}
