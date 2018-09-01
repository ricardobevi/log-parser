package com.ef.mapper.exceptions;

public class BadArgumentsException extends RuntimeException{

	private static final long serialVersionUID = 5936665131595785861L;
	
	private final String message;
	
	public BadArgumentsException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	

}
