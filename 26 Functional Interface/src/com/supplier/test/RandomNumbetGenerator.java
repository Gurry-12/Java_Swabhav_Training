package com.supplier.test;

import java.util.function.Supplier;

public class RandomNumbetGenerator {

	public static void main(String[] args) {
	
		Supplier<Double> random = () -> Math.random();
		
		System.out.println("5 Random Numbers between 0 and 1:\n");

        for (int i = 1; i <= 5; i++) {
            System.out.printf("Random Number %d: %.6f%n", i, random.get());
        }

	}

}
