package com.ef.mocks;

import java.util.ArrayList;
import java.util.List;

import com.ef.entities.BloquedIps;
import com.ef.entities.InputArgs;
import com.ef.entities.LogLine;
import com.ef.gateways.DatabaseGateway;

public class DatabaseGatewayMock implements DatabaseGateway {
	
	List<LogLine> savedLogLines;
	BloquedIps bloquedIps;

	public DatabaseGatewayMock(){
		savedLogLines = new ArrayList<LogLine>();
	}
	
	public DatabaseGatewayMock(BloquedIps bloquedIps){
		this();
		this.bloquedIps = bloquedIps;
	}
	
	public LogLine getFirst() {
		return this.savedLogLines.get(0);
	}

	@Override
	public void saveLog(LogLine logLine) {
		
		this.savedLogLines.add(logLine);
		
	}

	@Override
	public BloquedIps findBlockedIps(InputArgs inputArgs) {
		return bloquedIps;
	}

}
