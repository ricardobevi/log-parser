package com.ef.gateways.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ef.entities.Hourly;
import com.ef.entities.InputArgs;
import com.ef.gateways.FileGateway;
import com.ef.gateways.impl.exceptions.AccessLogException;
import com.ef.mocks.LogFileProcessorMock;

public class FileGatewayImplTest {
	
	private FileGateway fileGateway;
	
	@Before
	public void setup() {
		this.fileGateway = new FileGatewayImpl();
	}
	
	@Test
	public void given_a_valid_log_file_it_should_read_it() {
		
		LogFileProcessorMock logFileProcessor = new LogFileProcessorMock();
		
		this.fileGateway.readLogLines(new InputArgs("src/test/resources/samplelog.log", new Date(), new Hourly(), 0), logFileProcessor);
		
		Assert.assertEquals(
				"2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"", 
				logFileProcessor.logLines.get(0)
		);
		
		Assert.assertEquals(
				"2017-01-01 00:00:21.164|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"", 
				logFileProcessor.logLines.get(1)
		);
		
	}
	
	@Test
	public void given_that_there_is_no_log_file_it_should_not_process_it() {
		
		LogFileProcessorMock logFileProcessor = new LogFileProcessorMock();
		
		this.fileGateway.readLogLines(new InputArgs(null, new Date(), new Hourly(), 0), logFileProcessor);
		
		Assert.assertEquals(0, logFileProcessor.logLines.size());
		
		
	}
	
	
	@Test(expected = AccessLogException.class)
	public void given_an_empty_path_should_throw_an_AccessLogexception() {
				
		this.fileGateway.readLogLines(new InputArgs("", new Date(), new Hourly(), 0), new LogFileProcessorMock());
				
	}
	
	@Test(expected = AccessLogException.class)
	public void given_an_invalid_path_should_throw_an_AccessLogexception() {
				
		this.fileGateway.readLogLines(new InputArgs("src/test/inivisibleFolder", new Date(), new Hourly(), 0), new LogFileProcessorMock());
				
	}

}
