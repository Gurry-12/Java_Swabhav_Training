package com.gurpreet.interfaces.models;

import java.util.Random;
import com.gurpreet.interfaces.interfaces.Vehicle;

public class Car implements Vehicle {
	private final String fuel ;
	private static final Random random = new Random();

	public Car() {
		String[] fuels = {"Petrol", "Diesel"};
        this.fuel = fuels[random.nextInt(fuels.length)];
	}
	@Override
	public void start() {
		System.out.println("Car: Engine Start.... ");

	}

	@Override
	public void stop() {
		System.out.println("Car: Engine Stop....");

	}

	@Override
	public void fuelType() {
		System.out.println("Car: the fuel type is : " + fuel);

	}

}
