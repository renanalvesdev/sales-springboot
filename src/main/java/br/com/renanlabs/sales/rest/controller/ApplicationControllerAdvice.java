package br.com.renanlabs.sales.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.renanlabs.sales.exception.BusinessException;
import br.com.renanlabs.sales.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleBusinessException (BusinessException ex) {
		String errorMessage = ex.getMessage();
		return new ApiErrors(errorMessage);
	}
	
}
