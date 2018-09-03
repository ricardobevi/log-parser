package com.ef.entities.exceptions;

public class InvalidStartDateException extends RuntimeException {


	private static final long serialVersionUID = -5931554255128448224L;

	private final String startDate;
	
	public InvalidStartDateException(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public String getMessage() {
		return "Startdate " + this.startDate + "is invalid. Should be in format yyyy-MM-dd.HH:mm:ss";
	}
	
}
