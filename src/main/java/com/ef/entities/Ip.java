package com.ef.entities;

public class Ip {


	private final String ip;
	
	public Ip(String ip) {
		this.ip = ip;
	}

		
	@Override
	public String toString() {
		return ip;
	}


	public String getIp() {
		return ip;
	}


	

}
