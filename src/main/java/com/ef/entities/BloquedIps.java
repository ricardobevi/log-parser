package com.ef.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BloquedIps {
	
	private final List<Ip> bloquedIps;
	
	private final Integer treshold;
	private final Date startDate;
	private final Date endDate;


	public BloquedIps(List<Ip> bloquedIps, Integer treshold, Date startDate, Date endDate) {
		this.bloquedIps = bloquedIps;
		this.treshold = treshold;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
		
		return bloquedIps.stream().map(bloquedIp -> 
				bloquedIp.toString() + " has " + treshold + " or more requests between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate))
				.collect(Collectors.joining("\n"));
	}
	
	
	
}
