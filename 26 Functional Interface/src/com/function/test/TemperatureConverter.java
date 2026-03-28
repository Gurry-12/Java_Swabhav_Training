package com.function.test;

import java.util.function.Function;

public class TemperatureConverter {

	public static void main(String[] args) {

		Function<Double, Double> temprature = celsius -> (celsius * (9 / 5)) + 32;

		System.out.println(temprature.apply(0.0));
		System.out.println(temprature.apply((double) 20));
		System.out.println(temprature.apply((double) 37));
	}

}
