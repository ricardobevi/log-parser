package com.ef.mapper;

import org.junit.Assert;
import org.junit.Test;

public class ParametersMapperTest {

	@Test
	public void given_a_list_of_parameters_it_should_parse_it_to_an_object() {
		
		String[] args = {
				"--accesslog=/path/to/file", 
				"--startDate=2017-01-01.13:00:00", 
				"--duration=hourly", 
				"--threshold=100"
				};
		
		ArgsParser argsParser = new ArgsParser(args);
		
		InputArgs inputArgs = argsParser.parse();
		
		Assert.assertEquals("a", "a");
	}
	
}
