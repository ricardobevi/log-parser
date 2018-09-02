package com.ef.gateways;

import com.ef.entities.BloquedIps;
import com.ef.entities.InputArgs;
import com.ef.entities.LogLine;

public interface DatabaseGateway {

	void saveLog(LogLine logLine);

	BloquedIps findBlockedIps(InputArgs inputArgs);

}
