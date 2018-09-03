package com.ef.gateways.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ef.entities.InputArgs;
import com.ef.gateways.FileGateway;
import com.ef.gateways.LogFileProcessor;
import com.ef.gateways.impl.exceptions.AccessLogException;

public class FileGatewayImpl implements FileGateway{

	@Override
	public void readLogLines(InputArgs inputArgs, LogFileProcessor logFileProcessor) {
		
		try {
			
			inputArgs.getAccessLog().ifPresent( accessLog -> {
				
				try {
					
					BufferedReader br = Files.newBufferedReader(Paths.get(accessLog));
					logFileProcessor.process(br.lines());
					
				} catch (IOException e) {
					throw new AccessLogException(inputArgs.getAccessLog().orElse(""));
				}
				
				
			});

			
		} catch (UncheckedIOException e) {
			
			throw new AccessLogException(inputArgs.getAccessLog().orElse(""));
			
		}
		
	}

}
