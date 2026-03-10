package com.gurpreet.oopsassignment.models;

import com.gurpreet.oopsassignment.enums.EngineType;

public class Bike extends Vehicle {
	 private EngineType engineCC;

	 public Bike(String ownerName, double baseUsageCharge, EngineType engineCC) {
	     super(ownerName, baseUsageCharge);
	     if (engineCC == null) {
	         throw new IllegalArgumentException("Engine CC must not be null.");
	     }
	     this.engineCC = engineCC;
	 }

	 public EngineType getEngineCC() { return engineCC; }

	 @Override
	 public void processDetails() {
	     System.out.println("Reg: " + getRegistrationId() + ", Owner: " + getOwnerName() + 
	                       ", Charge: $" + getBaseUsageCharge() + ", Engine: " + engineCC);
	 }
	}

