package com.qualiti.odontoSystem.exception;

import java.util.List;

public class ErrorResponse {
	public ErrorResponse(List<String> messages) {
		super();
		this.messages = messages;
	}

	// Specific errors in API request processing
	private List<String> messages;

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
