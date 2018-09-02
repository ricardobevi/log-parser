package com.ef.gateways;

import com.ef.mapper.InputArgsDto;

public interface FileGateway {

	void readLogLines(InputArgsDto inputArgsDto, LogFileProcessor logFileProcessor);

}
