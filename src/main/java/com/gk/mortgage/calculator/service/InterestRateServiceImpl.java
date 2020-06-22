package com.gk.mortgage.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gk.mortgage.calculator.domain.Messages;
import com.gk.mortgage.calculator.domain.interest.rate.InterestRatesResponse;
import com.gk.mortgage.calculator.exceptions.InterestRateServiceException;

import lombok.Setter;

@Service
public class InterestRateServiceImpl implements InterestRateService {

	@Setter
    @Value("${environment.interestrate.uri}")
	private String interestRateUri;
	
	private Messages messages;
	
	private RestTemplate restTemplate;


	@Autowired
    public InterestRateServiceImpl(RestTemplate restTemplate, Messages messages) {
		super();
		this.restTemplate = restTemplate;
		this.messages = messages;
	}
	
	@Override
	public InterestRatesResponse getRates() {
		try {
		InterestRatesResponse interestRatesResponse = restTemplate.getForObject(interestRateUri, InterestRatesResponse.class);
		return interestRatesResponse;
		} catch (RestClientException e) {
			throw new InterestRateServiceException("MC0003", messages.getProperty("error.MC0003.message"));
		}
	}
}
