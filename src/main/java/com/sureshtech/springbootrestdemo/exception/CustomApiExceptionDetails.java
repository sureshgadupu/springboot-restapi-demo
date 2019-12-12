package com.sureshtech.springbootrestdemo.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomApiExceptionDetails {
	
	private Date date;
	private String errorDetails;
	private String excetionMessage;
	
	public CustomApiExceptionDetails(Date date, String errorDetails, String excetionMessage) {
		super();
		this.date = date;
		this.errorDetails = errorDetails;
		this.excetionMessage = excetionMessage;
	}
	public CustomApiExceptionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public String getExcetionMessage() {
		return excetionMessage;
	}
	public void setExcetionMessage(String excetionMessage) {
		this.excetionMessage = excetionMessage;
	}

}
