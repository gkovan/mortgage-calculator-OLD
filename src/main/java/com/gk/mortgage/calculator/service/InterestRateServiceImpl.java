package com.gk.mortgage.calculator.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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
	
	@Setter
    @Value("${environment.interestrate.username}")
	private String username;
	
	@Setter
    @Value("${environment.interestrate.password}")
	private String password;
	
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
			HttpHeaders headers = new HttpHeaders();
		    String authStr = username + ":" + password;
		    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
		    headers.add("Authorization", "Basic " + base64Creds);
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<InterestRatesResponse> response = restTemplate.exchange(
					interestRateUri, HttpMethod.GET, entity, InterestRatesResponse.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			throw new InterestRateServiceException("MC0004", messages.getProperty("error.MC0004.message"), e);
		} catch (HttpServerErrorException e) {
			throw new InterestRateServiceException("MC0003", messages.getProperty("error.MC0003.message"), e);			
	    }catch (RestClientException e) {
			throw new InterestRateServiceException("MC0003", messages.getProperty("error.MC0003.message"), e);
		}
	}
}
