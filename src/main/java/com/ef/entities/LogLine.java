package com.ef.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LogLine {

	private final Date requestDate;
	private final Ip ip;
	private final String method;
	private final int statusCode;
	private final String userAgent;
	
	
	public LogLine(String logLine){

		List<String> splittedLogLine = Arrays.asList(logLine.split("\\|")).stream().map(s -> s.replace("\"", "")).collect(Collectors.toList());
		Date requestDate = new Date();
		
		try {
			requestDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(splittedLogLine.get(0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		this.requestDate = requestDate;
		this.ip = new Ip(splittedLogLine.get(1));
		this.method = splittedLogLine.get(2);
		this.statusCode = Integer.parseInt(splittedLogLine.get(3));
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogLine other = (LogLine) obj;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (statusCode != other.statusCode)
			return false;
		if (userAgent == null) {
			if (other.userAgent != null)
				return false;
		} else if (!userAgent.equals(other.userAgent))
			return false;
		return true;
	}

	
	

}
