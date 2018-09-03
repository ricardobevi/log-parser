package com.ef.gateways.impl.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SuppressWarnings("unused")
public class Request {

	@Id
	@GeneratedValue
	private Long id;
	
	
	private String ip;
	
	@Temporal(TemporalType.DATE)
	private Date requestDate;
	
	private String method;
	private Integer statusCode;
	private String userAgent;
	
	public Request(String ip, Date requestDate, String method, Integer statusCode, String userAgent) {
		this.ip = ip;
		this.requestDate = requestDate;
		this.method = method;
		this.statusCode = statusCode;
		this.userAgent = userAgent;
	}
	
	
}
