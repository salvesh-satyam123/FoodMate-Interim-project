package com.cts.foodmate.exception;

public class UserNotExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public UserNotExistException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
