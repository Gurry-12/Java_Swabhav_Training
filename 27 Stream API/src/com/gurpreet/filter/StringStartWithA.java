package com.gurpreet.filter;

import java.util.List;
import java.util.stream.Collectors;

public class StringStartWithA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = List.of("Alice", "Bob", "Aarav", "Charlie", "Ananya", "David", "Emma", "Arjun", "Frank",
				"Aisha", "George", "Hannah", "Amit", "Iris", "Jack");

		List<String> namesStartWithA = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());
		
		System.out.println("Names Start with A");
		for(String name : namesStartWithA) {
			System.out.println(name);
		}
	}

}
