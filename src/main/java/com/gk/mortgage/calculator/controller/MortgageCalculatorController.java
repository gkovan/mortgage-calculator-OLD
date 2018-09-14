package com.gk.mortgage.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gk.mortgage.calculator.domain.MortgageCalculatorRequest;
import com.gk.mortgage.calculator.domain.MortgageCalculatorResponse;
import com.gk.mortgage.calculator.task.MortgageTask;

@RestController
public class MortgageCalculatorController {
	
	private MortgageTask mortgageTask;
	
	@Autowired
	public MortgageCalculatorController(MortgageTask mortgageTask) {
		this.mortgageTask = mortgageTask;
	}
	
	
	@GetMapping("/hellomortgage")
	public String mortgageCalculator() {
		return "mortgage";
	}
	
	
	
	@GetMapping("/calculate")
	public MortgageCalculatorResponse calculateMonthlyPayment(@RequestParam(value="principal") String principal, 
										  @RequestParam(value="interestrate") String interestRate,
										  @RequestParam(value="term") String term) {
		
		Double p = Double.parseDouble(principal);
		Double ir = Double.parseDouble(interestRate);
		Integer t = Integer.parseInt(term);		
		MortgageCalculatorRequest request = new MortgageCalculatorRequest();
		request.setPrincipal(p);
		request.setInterestRate(ir);
		request.setTerm(t);
		
		return mortgageTask.calculate(request);
	}
	
	@PostMapping("/calculate")
	public MortgageCalculatorResponse calculateMonthlyPayment(@RequestBody MortgageCalculatorRequest request) {		
		return mortgageTask.calculate(request);
	}

}
