package com.ef.entities.exceptions;

public class ParsingLogLineException extends RuntimeException {

	private static final long serialVersionUID = 1063074362864026134L;

	private final String logLine;
	
	public ParsingLogLineException(String logLine) {
		this.logLine = logLine;
	}

	@Override
	public String getMessage() {
		return "There was an error parsing log line " + this.logLine;
	}
	
	

}
