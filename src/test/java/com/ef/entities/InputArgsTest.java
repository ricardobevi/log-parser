package com.ef.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.ef.entities.exceptions.InvalidStartDateException;
import com.ef.entities.exceptions.InvalidThresholdException;
import com.ef.entities.exceptions.InvalidTimePeriodException;
import com.ef.mapper.InputArgsDto;


public class InputArgsTest {

	@Test
	public void given_inputargsdto_should_create_inputargs_bussiness_entity() throws ParseException {
		InputArgs inputArgs = new InputArgs( new InputArgsDto("/path/to/file", "2017-01-01.13:00:00", "hourly", "200") );
		
		
		Date expectedDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").parse("2017-01-01.13:00:00");
		InputArgs expectedInputArgs = new InputArgs("/path/to/file", expectedDate, new Hourly(), 200);
		
		Assert.assertEquals(inputArgs.toString(), expectedInputArgs.toString());
	} 
	
	
	@Test(expected=InvalidStartDateException.class)
	public void given_inputargsdto_with_invalid_start_date_shoud_throw_InvalidStartDateException(){
		new InputArgs( new InputArgsDto("/path/to/file", "2017-01-01  13:00:00", "hourly", "200") );
	} 
	
	@Test(expected=InvalidTimePeriodException.class)
	public void given_inputargsdto_with_invalid_time_period_shoud_throw_InvalidTimePeriodException(){
		new InputArgs( new InputArgsDto("/path/to/file", "2017-01-01.13:00:00", "yearly", "200") );
	} 
	
	@Test(expected=InvalidThresholdException.class)
	public void given_inputargsdto_with_invalid_threshold_shoud_throw_InvalidThresholdException(){
		new InputArgs( new InputArgsDto("/path/to/file", "2017-01-01.13:00:00", "daily", "threshold") );
	} 
	
}
