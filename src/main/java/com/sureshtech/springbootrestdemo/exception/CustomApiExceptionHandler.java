package com.sureshtech.springbootrestdemo.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Note : either use @ContrtollerAdvice or @RestControllerAdvicea annotation for global exception handling
 */


@ControllerAdvice
public class CustomApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		CustomApiExceptionDetails ce = new CustomApiExceptionDetails(new Date(), "Error from MethodArgumentNotValid method",ex.getMessage());
		//return handleExceptionInternal(ex, null, headers, status, request);
		return new ResponseEntity<Object>(ce,HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(ConstraintViolationException.class)
//	protected ResponseEntity<Object> handleConstraintViolatedException(
//			ConstraintViolationException ex,  WebRequest request) {
//		
//		CustomApiExceptionDetails ce = new CustomApiExceptionDetails(new Date(), "Error from ConstraintViolatedException method",ex.getMessage());
//		//return handleExceptionInternal(ex, null, headers, status, request);
//		return new ResponseEntity<Object>(ce,HttpStatus.BAD_REQUEST);
//		
//	}
	
}
