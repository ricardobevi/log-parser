package com.ef.mocks;

import java.util.Arrays;
import java.util.List;

import com.ef.gateways.FileGateway;
import com.ef.gateways.LogFileProcessor;
import com.ef.mapper.InputArgsDto;

public class FileGatewayMock implements FileGateway {

	private final List<String> logLines;
	
	
	public FileGatewayMock(String... sampleLogLines) {
		this.logLines = Arrays.asList(sampleLogLines);
	}

	public void readLogLines(InputArgsDto inputArgsDto, LogFileProcessor logFileProcessor) {
		
		logFileProcessor.process(logLines.parallelStream());
		
	}

}
