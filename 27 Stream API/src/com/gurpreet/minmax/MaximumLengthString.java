package com.gurpreet.minmax;

import java.util.Comparator;
import java.util.List;

public class MaximumLengthString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = List.of("Alice", "Bob", "Aarav", "Charlie", "Ananya", "David", "Emma", "Arjun", "Frank",
				"Aisha", "George", "Hannah", "Amit", "Iris", "Jack", "Gurpreet");

		String maxLengthString = names.stream().max(Comparator.comparingInt(String::length)).orElse("");
		
		System.out.println(maxLengthString);
	}

}
