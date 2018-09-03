package com.ef.entities.exceptions;

public class InvalidThresholdException extends RuntimeException {

	private static final long serialVersionUID = -6157315923325224127L;

	private final String threshold;
	
	public InvalidThresholdException(String threshold) {
		this.threshold = threshold;
	}

	@Override
	public String getMessage() {
		return "Threshold " + this.threshold + "is invalid";
	}
	
	
}
