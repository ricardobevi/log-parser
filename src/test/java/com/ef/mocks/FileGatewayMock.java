package com.ef.mocks;

import java.util.Arrays;
import java.util.List;

import com.ef.entities.InputArgs;
import com.ef.gateways.FileGateway;
import com.ef.gateways.LogFileProcessor;

public class FileGatewayMock implements FileGateway {

	private final List<String> logLines;
	
	
	public FileGatewayMock(String... sampleLogLines) {
		this.logLines = Arrays.asList(sampleLogLines);
	}

	public void readLogLines(InputArgs inputArgs, LogFileProcessor logFileProcessor) {
		logFileProcessor.process(logLines.parallelStream());		
	}

}
