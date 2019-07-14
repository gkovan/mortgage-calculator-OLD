package com.gk.mortgage.calculator.domain;

import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;
import com.gk.mortgage.calculator.annotations.Mask;

import lombok.Data;

@Data
@Mask
public class MortgageCalculatorResponse {
	
	@PII
	String name;
	@PII
	String propertyAddress;
	@PCI(keepLastDigits=4)
	String creditCard;
	@PCI
	String creditCardExpiry;
	@PII(keepLastDigits=4)
	String socialSecurityNumber;
	
	Double principal;
	Double interestRate;
	Integer term;
	String type;
	Double monthlyPayment;
}
