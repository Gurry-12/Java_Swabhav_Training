package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.Vehicle;

public class Bike implements Vehicle {
	
	private final String fuel = "Petrol";
	
	@Override
	public void start() {
		System.out.println("Bike: Engine Start ....");

	}

	@Override
	public void stop() {
		System.out.println("Bike: Engine Stop ....");

	}

	@Override
	public void fuelType() {
		System.out.println("Bike: the fuel type is : " + fuel);

	}

}
