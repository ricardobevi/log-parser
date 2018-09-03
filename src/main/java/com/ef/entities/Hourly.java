package com.ef.entities;

public class Hourly implements TimePeriod{

	public Integer inHours() {
		return 1;
	}

	@Override
	public String toString() {
		return "Hourly []";
	}
	
}
