package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

	private String message;

	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.message=message;
	}

	

}
