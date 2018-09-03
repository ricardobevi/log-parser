package com.ef.gateways;

import java.util.stream.Stream;

import com.ef.entities.BlockedIps;
import com.ef.entities.InputArgs;
import com.ef.entities.LogLine;

public interface DatabaseGateway {

	void saveLog(Stream<LogLine> logLineStream);

	BlockedIps findBlockedIps(InputArgs inputArgs);

}
