package com.ef.usecases;

import com.ef.entities.BlockedIps;

public class IpResquestsOutput {
	
	private final BlockedIps bloquedIps;

	public IpResquestsOutput(BlockedIps bloquedIps) {
		this.bloquedIps = bloquedIps;
	}
	
	public String toConsoleFormattedString() {
		return bloquedIps.toString();
	}

}
