package com.gurpreet.sorted;

import java.util.Comparator;
import java.util.List;

public class SortNameByLength {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = List.of("Alice", "Bob", "Aarav", "Charlie", "Ananya", "David", "Emma", "Arjun", "Frank",
				"Aisha", "George", "Hannah", "Amit", "Iris", "Jack");

		names.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
	}

}
