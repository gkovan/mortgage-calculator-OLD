package com.gk.mortgage.calculator.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.mortgage.calculator.domain.Messages;
import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.exceptions.BadRequestInputException;

@Service
public class ValidateInputTaskImpl implements ValidateInputTask {
	
	
	private Messages messages;
	
	@Autowired
	public ValidateInputTaskImpl(Messages messages) {
		this.messages = messages;
	}

	@Override
	public void validate(MortgageCalculatorRequest request) {
		if (request == null) 
			throw new BadRequestInputException("MC0000", messages.getProperty("error.MC0000.message"));
		
		if (request.getPrincipal() == null)
			throw new BadRequestInputException("MC0001", messages.getProperty("error.MC0001.message"));
		
		if (request.getTerm() == null)
			throw new BadRequestInputException("MC0002", messages.getProperty("error.MC0002.message"));
	}

}
