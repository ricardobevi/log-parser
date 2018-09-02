package com.ef.entities;

public class Hourly implements TimePeriod{

	public Integer inHours() {
		return 1;
	}

	@Override
	public boolean equals(TimePeriod other) {
		return other.inHours() == this.inHours();
	}
	
}
