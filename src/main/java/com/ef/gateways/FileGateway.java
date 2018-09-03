package com.ef.gateways;

import com.ef.entities.InputArgs;

public interface FileGateway {

	void readLogLines(InputArgs inputArgs, LogFileProcessor logFileProcessor);

}
