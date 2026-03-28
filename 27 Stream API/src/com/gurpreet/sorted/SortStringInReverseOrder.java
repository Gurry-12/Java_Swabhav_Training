package com.gurpreet.sorted;

import java.util.Comparator;
import java.util.List;

public class SortStringInReverseOrder {
	public static void main(String[] args) {
		List<String> names = List.of("Alice", "Bob", "Aarav", "Charlie", "Ananya", "David", "Zues","Emma", "Arjun", "Frank",
				"Aisha", "George", "Hannah", "Amit", "Iris", "Jack", "Maya");

		names.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
	}
}
