package com.ef;

import com.ef.mapper.ArgsParser;
import com.ef.mapper.GnuArgsParser;
import com.ef.mapper.exceptions.BadArgumentsException;

public class Parser {
	
	public static void main(String[] args) {
		
		ArgsParser argsParser = new GnuArgsParser();
		
		try {
			
			argsParser.parse(args);
		
		} catch (BadArgumentsException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		
		System.exit(0);
	}

}
