package com.ef.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.ef.entities.BlockedIps;
import com.ef.entities.InputArgs;
import com.ef.entities.LogLine;
import com.ef.gateways.DatabaseGateway;

public class DatabaseGatewayMock implements DatabaseGateway {
	
	List<LogLine> savedLogLines;
	BlockedIps bloquedIps;

	public DatabaseGatewayMock(){
		savedLogLines = new ArrayList<LogLine>();
	}
	
	public DatabaseGatewayMock(BlockedIps bloquedIps){
		this();
		this.bloquedIps = bloquedIps;
	}
	
	public LogLine getFirst() {
		return this.savedLogLines.get(0);
	}

	@Override
	public BlockedIps findBlockedIps(InputArgs inputArgs) {
		return bloquedIps;
	}

	@Override
	public void saveLog(Stream<LogLine> logLineStream) {
		logLineStream.forEach(logLine -> this.savedLogLines.add(logLine));
	}
	

}
