package com.gurpreet.skip;

import java.util.List;

public class FilterEvenAndSkipTwoInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = List.of(12, 45, 67, 34, 89, 22, 56, 78, 90);

		numbers.stream()
		       .filter(n -> n % 2 == 0)
		       .skip(2)
		       .forEach(System.out::println);
	}

}
