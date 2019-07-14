package com.gk.mortgage.calculator.logging;

public enum MaskNames {

	// Name use for search and masked related value on HTTP headers Map.
	// Any enum name added here will result on HTTP headers element with same name to be masked.
	// Make sure the enum name same as enum name value as the enum.values() is being
	// use for compare value as well.
	CLIENT_SECRET("client_secret");

	private String name;

	MaskNames(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static boolean contains(String test) {

		for (MaskNames c : MaskNames.values()) {
			if (c.name().equalsIgnoreCase((test))) {
				return true;
			}
		}

		return false;
	}
}
