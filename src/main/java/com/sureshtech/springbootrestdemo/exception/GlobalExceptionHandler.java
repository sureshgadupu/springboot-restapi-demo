package com.sureshtech.springbootrestdemo.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


/*
 * Note : either use @ContrtollerAdvice or @RestControllerAdvicea annotation for global exception handling
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected CustomApiExceptionDetails handleConstraintViolatedException(
			ConstraintViolationException ex,  WebRequest request) {
		
		CustomApiExceptionDetails ce = new CustomApiExceptionDetails(new Date(), "Error from ConstraintViolatedException method - Global",ex.getMessage());
		//return handleExceptionInternal(ex, null, headers, status, request);
		return ce;
		
	}

}
