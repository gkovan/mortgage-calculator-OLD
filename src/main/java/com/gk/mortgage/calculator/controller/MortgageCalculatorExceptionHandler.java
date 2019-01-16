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
import com.gk.mortgage.calculator.logging.MortgageCalculatorErrorCodes;

@ControllerAdvice
public class MortgageCalculatorExceptionHandler extends ResponseEntityExceptionHandler {
	
	// Respond with HTTP 500 Internal server error
	
    @ExceptionHandler({ RestClientException.class })
    protected ResponseEntity<Object> handleRestClientException(RuntimeException e, WebRequest request) {       
       ErrorResponse error = new ErrorResponse(MortgageCalculatorErrorCodes.APPERR0001.name(), MortgageCalculatorErrorCodes.APPERR0001.value());
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
       return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    } 
    
    // Respond with HTTP 400 Invalid input
    
    @ExceptionHandler({ BadRequestInputException.class })
    protected ResponseEntity<Object> handleBadRequestInputException(RuntimeException e, WebRequest request) {       
       ErrorResponse error = new ErrorResponse(MortgageCalculatorErrorCodes.APPERR0002.name(), MortgageCalculatorErrorCodes.APPERR0002.value() );
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
       return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
    } 
}
