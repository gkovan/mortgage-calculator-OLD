package com.gk.mortgage.calculator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gk.mortgage.calculator.domain.Messages;
import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.task.MortgageProcessorTask;
import com.gk.mortgage.calculator.task.MortgageProcessorTaskImpl;
import com.gk.mortgage.calculator.task.ValidateInputTask;
import com.gk.mortgage.calculator.task.ValidateInputTaskImpl;

@RestController
public class MortgageCalculatorController_NoDependencyInjection {
		
	@PostMapping("/calculate-no-dependency-injection")
	public MortgageCalculatorResponse calculateMonthlyPayment(@RequestBody MortgageCalculatorRequest request) {		
		
		   MortgageProcessorTask mortgageProcessorTask = new MortgageProcessorTaskImpl();
		   ValidateInputTask validateInputTask = new ValidateInputTaskImpl(new Messages());	
		   
		   validateInputTask.validate(request);
		   return mortgageProcessorTask.process(request);
	}
}