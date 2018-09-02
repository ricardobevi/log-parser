package com.ef.usecases;

import com.ef.entities.BloquedIps;

public class IpResquestsOutput {
	
	private final BloquedIps bloquedIps;

	public IpResquestsOutput(BloquedIps bloquedIps) {
		this.bloquedIps = bloquedIps;
	}
	
	public String toConsoleFormattedString() {
		return bloquedIps.toString();
	}

}
