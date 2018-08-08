package com.ahi;

public class AHCustomException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public AHCustomException() {
		super();
	}

	public AHCustomException(String errorMessage) {
		super();
	this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
