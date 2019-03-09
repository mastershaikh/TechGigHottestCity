package com.techgig.hotcity.exception;

public class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5695481785940071833L;
	
	private String message;
	
	public CityNotFoundException(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
