package com.qualiti.odontoSystem.exception;

public class DuplicateDataException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public DuplicateDataException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
