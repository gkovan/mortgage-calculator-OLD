package com.gk.mortgage.calculator.domain;

import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;
import com.gk.mortgage.calculator.annotations.Mask;

import lombok.Data;

@Data
@Mask
public class MortgageCalculatorRequest {
	
	@PII
	String name;
	@PCI(keepLastDigits=4)
	String creditCard = "4111111111111111";
	Double principal;
	Double interestRate;
	Integer term;
	String type;
}
