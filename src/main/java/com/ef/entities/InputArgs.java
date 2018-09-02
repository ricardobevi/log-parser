package com.ef.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ef.entities.exceptions.InvalidStartDateException;
import com.ef.entities.exceptions.InvalidThresholdException;
import com.ef.entities.exceptions.InvalidTimePeriodException;
import com.ef.mapper.InputArgsDto;

public class InputArgs {
	
	private final String accessLog;
	private final Date startDate;
	private final TimePeriod timePeriod;
	private final Integer threshold;
	
	private static final Map<String, TimePeriod> timePeriodMapper = new HashMap<String, TimePeriod>(){
		private static final long serialVersionUID = 5207867956537758499L;
	{
		put("hourly", new Hourly());
		put("daily", new Daily());
	}};
	

	public InputArgs(String accessLog, Date startDate, TimePeriod timePeriod, Integer threshold) {
		this.accessLog = accessLog;
		this.startDate = startDate;
		this.timePeriod = timePeriod;
		this.threshold = threshold;
	}

	public InputArgs(InputArgsDto inputArgsDto) {
		this.accessLog = inputArgsDto.getAccessLog();
		
		Date startDate = new Date();
		
		try {
			
			startDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").parse(inputArgsDto.getStartDate());
			
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
		if (timePeriod == null) {
			if (other.timePeriod != null)
				return false;
		} else if (!timePeriod.equals(other.timePeriod))
			return false;
		return true;
	}


	
	
}
