package com.ef.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.ef.mapper.InputArgsDto;


public class InputArgsTest {

	@Test
	public void given_inputargsdto_should_create_inputargs_bussiness_entity() throws ParseException {
		InputArgs inputArgs = new InputArgs( new InputArgsDto("/path/to/file", "2017-01-01.13:00:00", "hourly", "200") );
		
		
		Date expectedDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").parse("2017-01-01.13:00:00");
		InputArgs expectedInputArgs = new InputArgs("/path/to/file", expectedDate, new Hourly(), 200);
		
		Assert.assertEquals(inputArgs.toString(), expectedInputArgs.toString());
	} 
	
}
