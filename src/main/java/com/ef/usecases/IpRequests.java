package com.ef.usecases;

import com.ef.entities.BlockedIps;
import com.ef.entities.InputArgs;
import com.ef.gateways.DatabaseGateway;
import com.ef.gateways.FileGateway;
import com.ef.mapper.InputArgsDto;

public class IpRequests {

	private final InputArgsDto inputArgsDto;
	private final FileGateway fileGateway;
	private final DatabaseGateway databaseGateway;
	
	public IpRequests(IpResquestsInput ipRequestsInput) {
		this.inputArgsDto = ipRequestsInput.getInputArgsDto();
		this.fileGateway = ipRequestsInput.getFileGateway();
		this.databaseGateway = ipRequestsInput.getDatabaseGateway();
	}

	public IpResquestsOutput execute() {
		
		InputArgs inputArgs = new InputArgs(inputArgsDto);

		fileGateway.readLogLines(inputArgsDto, new LogLinesSaver(this.databaseGateway));
		
		BlockedIps bloquedIps = this.databaseGateway.findBlockedIps(inputArgs);

		return new IpResquestsOutput(bloquedIps);
	}

}
