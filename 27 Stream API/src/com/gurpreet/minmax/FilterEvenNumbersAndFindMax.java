package com.gurpreet.minmax;

import java.util.Comparator;
import java.util.List;

public class FilterEvenNumbersAndFindMax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = List.of(2, 5, 8, 10, 15, 20, 25, 30, 1, 5, 7, 4, 87, -33, 88);

		Integer evenHighestNumber = numbers.stream().filter(number -> number % 2 == 0).max(Comparator.naturalOrder())
				.get();

		System.out.println(evenHighestNumber);
	}

}
