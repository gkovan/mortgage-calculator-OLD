package com.gk.mortgage.calculator.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gk.mortgage.calculator.domain.ErrorResponse;
import com.gk.mortgage.calculator.exceptions.BadRequestInputException;
import com.gk.mortgage.calculator.exceptions.InterestRateServiceException;
import com.gk.mortgage.calculator.logging.MortgageCalculatorErrorCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class MortgageCalculatorExceptionHandler extends ResponseEntityExceptionHandler {

	// Respond with HTTP 400 Bad request invalid input

	@ExceptionHandler({ BadRequestInputException.class })
	protected ResponseEntity<Object> handleBadRequestInputException(BadRequestInputException e, WebRequest request) {
		log.error("Bad Request Input Exception: ", e);
		ErrorResponse error = new ErrorResponse(e.getErrorCode(), e.getLocalizedMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
	}

	// Respond with HTTP 500 Internal server error

	@ExceptionHandler({ InterestRateServiceException.class })
	protected ResponseEntity<Object> handleRestClientException(InterestRateServiceException e, WebRequest request) {
		log.error("Error invoking interest rate service: ", e);
		ErrorResponse error = new ErrorResponse(e.getErrorCode(), e.getLocalizedMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
