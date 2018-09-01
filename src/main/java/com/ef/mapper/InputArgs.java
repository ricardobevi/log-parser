package com.ef.mapper;

public class InputArgs {
	
	private final String accessLog;
	private final String startDate;
	private final String duration;
	private final String threshold;
	
	
	
	public InputArgs(String accessLog, String startDate, String duration, String threshold) {
		this.accessLog = accessLog;
		this.startDate = startDate;
		this.duration = duration;
		this.threshold = threshold;
	}


	public InputArgs() {
		this.accessLog = "";
		this.startDate = "";
		this.duration = "";
		this.threshold = "";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputArgs other = (InputArgs) obj;
		if (accessLog == null) {
			if (other.accessLog != null)
				return false;
		} else if (!accessLog.equals(other.accessLog))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (threshold == null) {
			if (other.threshold != null)
				return false;
		} else if (!threshold.equals(other.threshold))
			return false;
		return true;
	}

	


}
