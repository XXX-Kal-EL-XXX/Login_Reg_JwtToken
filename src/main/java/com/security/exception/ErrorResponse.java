package com.security.exception;

public class ErrorResponse {
	
	String backResponse;

	public String getBackResponse() {
		return backResponse;
	}

	public void setBackResponse(String backResponse) {
		this.backResponse = backResponse;
	}

	public ErrorResponse(String backResponse) {
		super();
		this.backResponse = backResponse;
	}

}
