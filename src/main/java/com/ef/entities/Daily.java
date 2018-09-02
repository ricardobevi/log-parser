package com.ef.entities;

public class Daily implements TimePeriod {

	public Integer inHours() {
		return 24;
	}

	@Override
	public boolean equals(TimePeriod other) {
		return other.inHours() == this.inHours();
	}

}
