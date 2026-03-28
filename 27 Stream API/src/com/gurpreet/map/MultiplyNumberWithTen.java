package com.gurpreet.map;

import java.util.List;

public class MultiplyNumberWithTen {

	public static final int MULTIPLY_NUMBER = 10;

	public static void main(String[] args) {
			
		// TODO Auto-generated method stub
		List<Integer> numbers = List.of(2, 5, 8, 10, 15, 20, 25, 30);
		
		numbers.stream().map(number -> number * MULTIPLY_NUMBER ).forEach(System.out::println);
	}

}
