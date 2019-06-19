package com.gk.mortgage.calculator.domain;

import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;
import com.gk.mortgage.calculator.annotations.Sensitive;

import lombok.Data;

@Data
@Sensitive
public class MortgageCalculatorResponse {
	Double principal;
	Double interestRate;
	Integer term;
	String type;

	Double monthlyPayment;
	@PCI
	String creditCard = "4111111111111111";
}
