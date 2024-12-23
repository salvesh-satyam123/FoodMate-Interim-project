package com.cts.foodmate.exception;


@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException{
	
	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException() {
		super();
		
	}
	
}
