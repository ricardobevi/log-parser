package com.ef.mapper;

public class InputArgsDto {
	
	private final String accessLog;
	private final String startDate;
	private final String duration;
	private final String threshold;
	
	
	
	public InputArgsDto(String accessLog, String startDate, String duration, String threshold) {
		this.accessLog = accessLog;
		this.startDate = startDate;
		this.duration = duration;
		this.threshold = threshold;
	}

	

	public String getAccessLog() {
		return accessLog;
	}



	public String getStartDate() {
		return startDate;
	}



	public String getDuration() {
		return duration;
	}



	public String getThreshold() {
		return threshold;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputArgsDto other = (InputArgsDto) obj;
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
