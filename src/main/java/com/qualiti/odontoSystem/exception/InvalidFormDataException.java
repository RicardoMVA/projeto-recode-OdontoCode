package com.qualiti.odontoSystem.exception;

public class InvalidFormDataException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	public InvalidFormDataException(String msg) {
		this.msg = msg;
	}

	public String getErrorMsg() {
		return this.msg;
	}

	@Override
	public String toString() {
//		retornar um json usando esse tostring, com a mensagem de erro
		// TODO Auto-generated method stub
		return super.toString();
	}

}
