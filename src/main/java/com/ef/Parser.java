package com.ef;

import com.ef.gateways.DatabaseGateway;
import com.ef.gateways.FileGateway;
import com.ef.gateways.impl.DatabaseGatewayImpl;
import com.ef.gateways.impl.FileGatewayImpl;
import com.ef.mapper.ArgsParser;
import com.ef.mapper.GnuArgsParser;
import com.ef.mapper.InputArgsDto;
import com.ef.usecases.IpRequests;
import com.ef.usecases.IpResquestsInput;
import com.ef.usecases.IpResquestsOutput;


public class Parser  {
	
	ArgsParser argsParser;
	
	FileGateway fileGateway;
	
	DatabaseGateway databaseGateway;
	
	
	public static void main(String[] args) {
		
		Parser parser = new Parser();
		
		parser.run(args);
		
	}
	
	
	public Parser(){
		this.argsParser = new GnuArgsParser();
		this.fileGateway = new FileGatewayImpl();
		this.databaseGateway = new DatabaseGatewayImpl();
	}
	
	public void run(String... args) {
		
		try {
			
			InputArgsDto inputArgsDto = argsParser.parse(args);
			
			IpResquestsInput ipRequestsInput = new IpResquestsInput(inputArgsDto, databaseGateway, fileGateway);
			
			IpResquestsOutput ipRequestsOutput = new IpRequests(ipRequestsInput).execute();
			
			System.out.println( ipRequestsOutput.toConsoleFormattedString() );
		
		} catch (RuntimeException e) {
			
			System.err.println(e.getMessage());
			System.exit(1);
			
		}

        System.exit(0);
    }

}
