package com.ef.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BlockedIps {
	
	private final List<Ip> blockedIps;
	
	private final Integer treshold;
	private final Date startDate;
	private final Date endDate;


	public BlockedIps(List<Ip> bloquedIps, Integer treshold, Date startDate, Date endDate) {
		this.blockedIps = bloquedIps;
		this.treshold = treshold;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		
		return blockedIps.stream().map(bloquedIp -> 
				bloquedComment(bloquedIp.toString()) )
				.collect(Collectors.joining("\n"));
	}
	
	public List<String> toList(){
		return blockedIps.stream().map(bloquedIp -> 
			bloquedComment(bloquedIp.toString()) )
			.collect(Collectors.toList());
	}
	
	private String bloquedComment(String bloquedIpString) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
		
		return bloquedIpString + " has " + treshold + " or more requests between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate);
	}
	
}
