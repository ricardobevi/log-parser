package com.ef.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ef.entities.exceptions.ParsingLogLineException;

public class LogLine {

	private final Date requestDate;
	
	private final Ip ip;
	
	private final String method;
	private final Integer statusCode;
	private final String userAgent;
	
		
	public LogLine(String logLine){

		List<String> splittedLogLine = Arrays.asList(logLine.split("\\|")).stream().map(s -> s.replace("\"", "")).collect(Collectors.toList());
		Date requestDate = new Date();
		Integer statusCode = 0;
		
		try {
			
			requestDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(splittedLogLine.get(0));
			statusCode = Integer.parseInt(splittedLogLine.get(3));
			
		} catch (ParseException | NumberFormatException e) {
			throw new ParsingLogLineException(logLine);
		} 

		this.requestDate = requestDate;
		this.ip = new Ip(splittedLogLine.get(1));
		this.method = splittedLogLine.get(2);
		this.statusCode = statusCode;
		this.userAgent = splittedLogLine.get(4);
	}

	public LogLine(Date expectedDate, Ip ip, String method, int statusCode, String userAgent) {
		this.requestDate = expectedDate;
		this.ip = ip;
		this.method = method;
		this.statusCode = statusCode;
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		return "LogLine [requestDate=" + requestDate + ", ip=" + ip + ", method=" + method
				+ ", statusCode=" + statusCode + ", userAgent=" + userAgent + "]";
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public Ip getIp() {
		return ip;
	}

	public String getMethod() {
		return method;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getUserAgent() {
		return userAgent;
	}

	
	
}
