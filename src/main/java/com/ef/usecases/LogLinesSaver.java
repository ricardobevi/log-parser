package com.ef.usecases;

import java.util.stream.Stream;

import com.ef.entities.LogLine;
import com.ef.gateways.DatabaseGateway;
import com.ef.gateways.LogFileProcessor;

public class LogLinesSaver implements LogFileProcessor {
	
	private final DatabaseGateway databaseGateway;

	public LogLinesSaver(DatabaseGateway databaseGateway) {
		this.databaseGateway = databaseGateway;
	}

	@Override
	public void process(Stream<String> logLinesStream) {
		databaseGateway.saveLog(logLinesStream.map(logLine -> new LogLine(logLine)));
	}

}
