package com.gk.mortgage.calculator.task;

import org.springframework.stereotype.Service;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.exceptions.BadRequestInputException;

@Service
public class ValidateInputTaskImpl implements ValidateInputTask {

	@Override
	public void validate(MortgageCalculatorRequest request) {
		if (request == null) 
			throw new BadRequestInputException("Input request object does not exist.");
		
		if (request.getPrincipal() == null)
			throw new BadRequestInputException("Princial amount does not exist.");
		
		if (request.getTerm() == null)
			throw new BadRequestInputException("Term amount does not exist.");
	}

}
