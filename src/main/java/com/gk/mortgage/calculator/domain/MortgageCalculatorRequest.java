package com.gk.mortgage.calculator.domain;

import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;
import com.gk.mortgage.calculator.annotations.Mask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Mask
@Builder(toBuilder = true)
public class MortgageCalculatorRequest {
	
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
}
