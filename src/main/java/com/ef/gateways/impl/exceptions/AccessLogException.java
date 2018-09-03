package com.ef.gateways.impl.exceptions;

public class AccessLogException extends RuntimeException {

	private static final long serialVersionUID = -937322408883145530L;

	private final String accessLog;
	
	public AccessLogException(String accessLog) {
		this.accessLog = accessLog;
	}

	@Override
	public String getMessage() {
		return "There was a problem accessing the file " + accessLog;
	}
	
	

}
