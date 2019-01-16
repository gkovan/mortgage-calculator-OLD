package com.gk.mortgage.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gk.mortgage.calculator.domain.interest.rate.InterestRatesResponse;

import lombok.Setter;

@Service
public class InterestRateServiceImpl implements InterestRateService {

	@Setter
    @Value("${environment.interestrate.uri}")
	private String interestRateUri;
	
	private RestTemplate restTemplate;


	@Autowired
    public InterestRateServiceImpl(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
	
	@Override
	public InterestRatesResponse getRates() {
		return restTemplate.getForObject(interestRateUri, InterestRatesResponse.class);
	}
}
