package com.gk.mortgage.calculator.domain;

import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;

import lombok.Data;

@Data
public class MortgageCalculatorResponse {
	Double principal;
	Double interestRate;
	Integer term;
	String type;
	@PII
	Double monthlyPayment;
	@PCI
	String creditCard = "4111111111111111";
}
