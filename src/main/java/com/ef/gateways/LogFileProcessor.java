package com.ef.gateways;

import java.util.stream.Stream;

public interface LogFileProcessor {

	void process(Stream<String> logLinesStream);

}
