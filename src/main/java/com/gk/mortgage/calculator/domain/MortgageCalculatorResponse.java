package com.gk.mortgage.calculator.domain;

import lombok.Data;

@Data
public class MortgageCalculatorResponse {
	Double principal;
	Double interestRate;
	Integer term;
	String type;
	Double monthlyPayment;
}
