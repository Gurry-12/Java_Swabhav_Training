package com.gurpreet.sorted;

import java.util.List;

public class SortStingInAlphabaticalOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> names = List.of("Alice", "Bob", "Aarav", "Charlie", "Ananya", "David", "Emma", "Arjun", "Frank",
				"Aisha", "George", "Hannah", "Amit", "Iris", "Jack");

		names.stream().sorted().forEach(System.out::println);
	}

}
