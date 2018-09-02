package com.ef.usecases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.ef.entities.BloquedIps;
import com.ef.entities.Ip;
import com.ef.entities.LogLine;
import com.ef.gateways.FileGateway;
import com.ef.mapper.InputArgsDto;
import com.ef.mocks.DatabaseGatewayMock;
import com.ef.mocks.FileGatewayMock;

public class IpRequestsTest {
	
	private static final String SAMPLE_LOG_1 = "2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
	private static final String SAMPLE_LOG_2 = "2017-01-01 00:00:21.164|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";

	
	@Test
	public void given_an_ip_it_should_save_it_to_the_db() {
		
		InputArgsDto inputArgsDto = new InputArgsDto("/samplelog.log", "2017-01-01.00:00:00", "hourly", "2");
		
		FileGateway fileGateway = new FileGatewayMock(SAMPLE_LOG_1);
		
		DatabaseGatewayMock databaseGateway = new DatabaseGatewayMock();
		
		LogLine expectedLogLine = new LogLine(SAMPLE_LOG_1);
				
		IpResquestsInput ipRequestsInput = new IpResquestsInput(inputArgsDto, databaseGateway, fileGateway);
		
		
		
		new IpRequests(ipRequestsInput).execute();
		
		
		
		Assert.assertEquals(expectedLogLine, databaseGateway.getFirst());
		
	}
	
	
	@Test
	public void given_an_ip_that_made_2_requests_and_a_threshold_of_2_should_return_that_ip() throws ParseException {
		
		InputArgsDto inputArgsDto = new InputArgsDto("/samplelog.log", "2017-01-01.00:00:00", "hourly", "2");
		
		FileGateway fileGateway = new FileGatewayMock(SAMPLE_LOG_1, SAMPLE_LOG_2);
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").parse("2017-01-01.00:00:00");
		Date endDate = Date.from(startDate.toInstant().plus(Duration.ofHours(1).minus(Duration.ofSeconds(1))));
		
		BloquedIps bloquedIps = new BloquedIps(Arrays.asList(new Ip("192.168.234.82")), 2, startDate, endDate);
		
		DatabaseGatewayMock databaseGateway = new DatabaseGatewayMock(bloquedIps);
						
		IpResquestsInput ipRequestsInput = new IpResquestsInput(inputArgsDto, databaseGateway, fileGateway);
		
		IpResquestsOutput ipRequestsOutput = new IpRequests(ipRequestsInput).execute();
		
		Assert.assertEquals("192.168.234.82 has 2 or more requests between 2017-01-01.00:00:00 and 2017-01-01.00:59:59", ipRequestsOutput.toConsoleFormattedString());
		
	}
	
}
