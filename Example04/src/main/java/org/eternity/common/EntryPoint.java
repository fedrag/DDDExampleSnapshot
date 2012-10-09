package org.eternity.common;

import org.eternity.customer.Customer;

public class EntryPoint {
	private final String identity;

	public EntryPoint(String identity) {
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}
}