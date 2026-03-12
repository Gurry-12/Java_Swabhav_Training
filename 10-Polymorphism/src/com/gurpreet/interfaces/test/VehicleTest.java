package com.gurpreet.interfaces.test;

import com.gurpreet.interfaces.interfaces.Vehicle;
import com.gurpreet.interfaces.models.Bike;
import com.gurpreet.interfaces.models.Car;

public class VehicleTest {

	public static void main(String[] args) {
		Vehicle[] vehicles = new Vehicle[4];

	    vehicles[0] = new Car();
	    vehicles[1] = new Bike();
	    vehicles[2] = new Car();
	    vehicles[3] = new Car();

	    System.out.println("=== Vehicle Demonstration ===\n");

	    int i = 1;
	    for (Vehicle v : vehicles) {
	        System.out.println("Vehicle " + i + " (" + v.getClass().getSimpleName() + "):");
	        v.start();
	        v.fuelType();
	        v.stop();
	        System.out.println("-----------------------------");
	        i++;
	    }

	}

}
