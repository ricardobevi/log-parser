package com.ef.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ef.entities.exceptions.InvalidStartDateException;
import com.ef.entities.exceptions.InvalidThresholdException;
import com.ef.entities.exceptions.InvalidTimePeriodException;
import com.ef.mapper.InputArgsDto;

public class InputArgs {
	
	private final Optional<String> accessLog;
	private final Date startDate;
	private final TimePeriod timePeriod;
	private final Integer threshold;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
	
	private static final Map<String, TimePeriod> timePeriodMapper = new HashMap<String, TimePeriod>(){
		private static final long serialVersionUID = 5207867956537758499L;
	{
		put("hourly", new Hourly());
		put("daily", new Daily());
	}};
	

	public InputArgs(String accessLog, Date startDate, TimePeriod timePeriod, Integer threshold) {
		this.accessLog = Optional.ofNullable(accessLog);
		this.startDate = startDate;
		this.timePeriod = timePeriod;
		this.threshold = threshold;
	}

	public InputArgs(InputArgsDto inputArgsDto) {
		this.accessLog = Optional.ofNullable(inputArgsDto.getAccessLog());
		
		Date startDate = new Date();
		
		try {
			
			startDate = dateFormat.parse(inputArgsDto.getStartDate());
			
		} catch (ParseException e) {
			throw new InvalidStartDateException(inputArgsDto.getStartDate());
		}

		this.startDate = startDate;
		
		this.timePeriod = Optional.ofNullable( timePeriodMapper.get(inputArgsDto.getDuration()) )
				.orElseThrow(() -> new InvalidTimePeriodException(inputArgsDto.getDuration()));
		
		try {
			
			this.threshold = Integer.parseInt(inputArgsDto.getThreshold());
			
		} catch (NumberFormatException e) {
			throw new InvalidThresholdException(inputArgsDto.getThreshold());
		}
	}
	
	
	

	public Optional<String> getAccessLog() {
		return accessLog;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return Date.from(startDate.toInstant().plus(Duration.ofHours(this.timePeriod.inHours()).minus(Duration.ofSeconds(1))));
	}

	public Integer getThreshold() {
		return threshold;
	}

	public String getStartDateString() {
		return dateFormat.format(getStartDate());
	}

	public String getEndDateString() {
		return dateFormat.format(getEndDate());
	}
	
	@Override
	public String toString() {
		return "InputArgs [accessLog=" + accessLog + ", startDate=" + startDate + ", timePeriod=" + timePeriod
				+ ", threshold=" + threshold + "]";
	}
	
	
}
