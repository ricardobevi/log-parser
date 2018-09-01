package com.ef.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.ef.mapper.exceptions.BadArgumentsException;

public class ArgsMapperTest {

	@Test
	public void given_a_list_of_args_it_should_parse_it_to_an_object() {
		
		String[] args = {
				"--accesslog=/path/to/file", 
				"--startDate=2017-01-01.13:00:00", 
				"--duration=hourly", 
				"--threshold=200"
				};
		
		ArgsParser gnuArgsParser = new GnuArgsParser();
		
		InputArgs inputArgs = gnuArgsParser.parse(args);
		
		InputArgs expectedInputArgs = new InputArgs("/path/to/file", "2017-01-01.13:00:00", "hourly", "200");
		
		Assert.assertEquals(expectedInputArgs, inputArgs);
	}
	
	@Test
	public void given_a_list_of_args_in_any_order_it_should_parse_it_to_an_object() {
		
		String[] args = {
				"--duration=hourly", 
				"--accesslog=/path/to/file", 
				"--threshold=200",
				"--startDate=2017-01-01.13:00:00" 
				};
		
		ArgsParser gnuArgsParser = new GnuArgsParser();
		
		InputArgs inputArgs = gnuArgsParser.parse(args);
		
		InputArgs expectedInputArgs = new InputArgs("/path/to/file", "2017-01-01.13:00:00", "hourly", "200");
		
		Assert.assertEquals(expectedInputArgs, inputArgs);
	}
	
	@Test(expected=BadArgumentsException.class)
	public void given_a_list_of_args_with_duration_missing_it_should_throw_a_BadArgumentsException() {
		
		String[] args = {
				"--accesslog=/path/to/file", 
				"--startDate=2017-01-01.13:00:00", 
				"--threshold=200"
				};
		
		ArgsParser gnuArgsParser = new GnuArgsParser();
		
	
		gnuArgsParser.parse(args);
		
	}
	
	
	@Test
	public void given_a_list_of_args_with_duration_missing_it_should_throw_a_BadArgumentsException_with_a_help_messsage() {
		
		String[] args = {
				"--accesslog=/path/to/file", 
				"--startDate=2017-01-01.13:00:00", 
				"--threshold=200"
				};
		
		ArgsParser gnuArgsParser = new GnuArgsParser();
		
		BadArgumentsException thrownException = new BadArgumentsException("");
		
		try {
			
			gnuArgsParser.parse(args);
		
		} catch (BadArgumentsException e) {
			thrownException = e;
		}
		
		Assert.assertEquals(
				"Missing required option: duration\n" + 
				"\n" + 
				"usage: parser.jar --accesslog <path> --duration <hourly,daily> --startDate <date> --threshold <int>\n" + 
				"    --accesslog <path>          Path to the access.log file\n" + 
				"    --duration <hourly,daily>   'hourly' or 'daily'\n" + 
				"    --startDate <date>          Stating point to meassure in \"yyyy-MM-dd.HH:mm:ss\" format\n" + 
				"    --threshold <int>           Number of request per IP to filter\n", 
				thrownException.getMessage());
		
		
	}
	
	
	@Test
	public void given_a_list_of_args_with_duration_and_threshold_missing_it_should_throw_a_BadArgumentsException_with_a_help_messsage() {
		
		String[] args = {
				"--accesslog=/path/to/file", 
				"--startDate=2017-01-01.13:00:00"
				};
		
		ArgsParser gnuArgsParser = new GnuArgsParser();
		
		BadArgumentsException thrownException = new BadArgumentsException("");
		
		try {
			
			gnuArgsParser.parse(args);
		
		} catch (BadArgumentsException e) {
			thrownException = e;
		}
		
		Assert.assertEquals(
				"Missing required options: duration, threshold\n" + 
				"\n" + 
				"usage: parser.jar --accesslog <path> --duration <hourly,daily> --startDate <date> --threshold <int>\n" + 
				"    --accesslog <path>          Path to the access.log file\n" + 
				"    --duration <hourly,daily>   'hourly' or 'daily'\n" + 
				"    --startDate <date>          Stating point to meassure in \"yyyy-MM-dd.HH:mm:ss\" format\n" + 
				"    --threshold <int>           Number of request per IP to filter\n", 
				thrownException.getMessage());
		
		
	}
	
	
}
