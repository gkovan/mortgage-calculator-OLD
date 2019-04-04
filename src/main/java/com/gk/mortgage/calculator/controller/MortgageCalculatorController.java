package com.gk.mortgage.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.task.MortgageProcessorTask;
import com.gk.mortgage.calculator.task.ValidateInputTask;

@RestController
public class MortgageCalculatorController {
	
	final private MortgageProcessorTask mortgageProcessorTask;
	
	final private ValidateInputTask validateInputTask;

	
	@Autowired
	public MortgageCalculatorController(MortgageProcessorTask mortgageProcessorTask, ValidateInputTask validateInputTask) {
		this.mortgageProcessorTask = mortgageProcessorTask;
		this.validateInputTask = validateInputTask;
	}	
	
	@PostMapping("/calculate")
	public MortgageCalculatorResponse calculateMonthlyPayment(@RequestBody MortgageCalculatorRequest request) {		
		   validateInputTask.validate(request);
		   return mortgageProcessorTask.process(request);
	}
}
