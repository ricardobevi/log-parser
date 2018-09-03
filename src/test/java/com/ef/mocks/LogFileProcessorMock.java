package com.ef.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.ef.gateways.LogFileProcessor;

public class LogFileProcessorMock implements LogFileProcessor {

	public List<String> logLines;
	
	public LogFileProcessorMock() {
		this.logLines = new ArrayList<String>();
	}
	
	@Override
	public void process(Stream<String> logLinesStream) {
		
		logLinesStream.forEach(logLine -> this.logLines.add(logLine));
		
	}

	

}
