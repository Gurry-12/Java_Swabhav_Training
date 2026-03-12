package com.gurpreet.oopsassignment.models;

import com.gurpreet.oopsassignment.enums.OilType;

public class Car extends Vehicle {
	 private OilType oilType;
	 private String color;

	 public Car(String ownerName, double baseUsageCharge, OilType oilType, String color) {
	     super(ownerName, baseUsageCharge);
	     if (oilType == null) {
	         throw new IllegalArgumentException("Oil type must not be null.");
	     }
	     if (color == null || color.trim().isEmpty()) {
	         throw new IllegalArgumentException("Color must not be null or empty.");
	     }
	     this.oilType = oilType;
	     this.color = color;
	 }

	 public OilType getOilType() { return oilType; }
	 public String getColor() { return color; }

	 @Override
	 public void processDetails() {
	     System.out.println("Reg: " + getRegistrationId() + ", Owner: " + getOwnerName() + 
	                       ", Charge: $" + getBaseUsageCharge() + ", Oil: " + oilType + ", Color: " + color);
	 }
	}

	
