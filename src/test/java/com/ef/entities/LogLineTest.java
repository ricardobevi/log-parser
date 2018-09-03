package com.ef.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

public class LogLineTest {

	@Test
	public void given_a_log_line_it_should_parse_it() throws ParseException {
		
		LogLine logLine = new LogLine("2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"");
			
		LogLine expectedLogLine = new LogLine(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2017-01-01 00:00:11.763"),
				new Ip("192.168.234.82"),
				"GET / HTTP/1.1",
				200,
				"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0");
		
		
		Assert.assertEquals(expectedLogLine.toString(), logLine.toString());
		
	}
	
}
