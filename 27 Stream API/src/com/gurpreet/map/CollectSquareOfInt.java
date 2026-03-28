package com.gurpreet.map;

import java.util.List;
import java.util.stream.Collectors;

public class CollectSquareOfInt {
	public static void main(String[] args) {

		List<Integer> numbers = List.of(2, 5, 8, 10, 15, 20, 25, 30);

		List<Integer> squareOfNumbers = numbers.stream().map(number -> number * number).collect(Collectors.toList());
		
		System.out.println("Original List : " + numbers);
		System.out.println("Square of each : " + squareOfNumbers);
	}
}
