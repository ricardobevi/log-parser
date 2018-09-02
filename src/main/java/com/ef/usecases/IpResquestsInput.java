package com.ef.usecases;

import com.ef.gateways.DatabaseGateway;
import com.ef.gateways.FileGateway;
import com.ef.mapper.InputArgsDto;

public class IpResquestsInput {
	
	private final InputArgsDto inputArgsDto;
	private final DatabaseGateway databaseGateway;
	private final FileGateway fileGateway;

	public IpResquestsInput(InputArgsDto inputArgsDto, DatabaseGateway databaseGateway, FileGateway fileGateway) {
		this.inputArgsDto = inputArgsDto;
		this.databaseGateway = databaseGateway;
		this.fileGateway = fileGateway;
	}

	public InputArgsDto getInputArgsDto() {
		return inputArgsDto;
	}

	public DatabaseGateway getDatabaseGateway() {
		return databaseGateway;
	}

	public FileGateway getFileGateway() {
		return fileGateway;
	}

	
	
	
}
