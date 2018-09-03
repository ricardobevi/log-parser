package com.ef.entities.exceptions;

public class InvalidTimePeriodException extends RuntimeException {

	private static final long serialVersionUID = 1017275197242964961L;

	private final String duration;
	
	public InvalidTimePeriodException(String duration) {
		this.duration = duration;
	}

	@Override
	public String getMessage() {
		return "Duration " + this.duration + "is invalid";
	}
	
}
