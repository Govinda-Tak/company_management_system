package com.example.demo.exception;

import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ApiException resourceNotFoundExceptionHandler(ResourceNotFoundException exception)
	{
		return new ApiException(404,exception.getMessage() , ZonedDateTime.now());
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ApiException invalidInputExceptionHandler(InvalidInputException exception)
	{
		return new ApiException(400,exception.getMessage() , ZonedDateTime.now());
	}
	
	@ExceptionHandler(EmployeeManagementException.class)
	public ApiException EmployeeManagementExceptionHandler(EmployeeManagementException exception)
	{
		return new ApiException(400,exception.getMessage() , ZonedDateTime.now());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ApiException ExceptionHandler(RuntimeException exception)
	{
		return new ApiException(500,exception.getMessage() , ZonedDateTime.now());
	}
	
	
}
