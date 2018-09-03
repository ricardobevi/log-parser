package com.ef.gateways.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ef.gateways.FileGateway;
import com.ef.gateways.LogFileProcessor;
import com.ef.gateways.impl.exceptions.AccessLogException;
import com.ef.mapper.InputArgsDto;

public class FileGatewayImpl implements FileGateway{

	@Override
	public void readLogLines(InputArgsDto inputArgsDto, LogFileProcessor logFileProcessor) {
		
		try {

			BufferedReader br = Files.newBufferedReader(Paths.get(inputArgsDto.getAccessLog()));
			
			logFileProcessor.process(br.lines());
			
		} catch (UncheckedIOException e) {
			
			throw new AccessLogException(inputArgsDto.getAccessLog());
			
		} catch (IOException e) {
			
			throw new AccessLogException(inputArgsDto.getAccessLog());
			
		}
		
	}

}
