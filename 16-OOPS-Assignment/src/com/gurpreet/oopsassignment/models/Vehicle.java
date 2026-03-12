package com.gurpreet.oopsassignment.models;

public abstract class Vehicle {
	private final static String prefix = "REG-V";
	private static long vehicleCounter = 0;
	private String registrationId;
	private String ownerName;
	private double baseUsageCharge;

	public Vehicle(String ownerName, double baseUsageCharge) {

		if (ownerName == null || ownerName.trim().isEmpty()) {
			throw new IllegalArgumentException("Owner name must not be null or empty.");
		}
		if (baseUsageCharge < 0) {
			throw new IllegalArgumentException("Base usage charge cannot be negative.");
		}
		this.registrationId = prefix + String.format("%04d", ++vehicleCounter);
		this.ownerName = ownerName;
		this.baseUsageCharge = baseUsageCharge;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public double getBaseUsageCharge() {
		return baseUsageCharge;
	}

	public abstract void processDetails();
}
