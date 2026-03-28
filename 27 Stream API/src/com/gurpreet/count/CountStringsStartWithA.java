package com.gurpreet.count;

import java.util.List;
import java.util.stream.Collectors;

public class CountStringsStartWithA {
public static void main(String[] args) {
	List<String> names = List.of("Alice", "Bob", "Aarav", "Charlie", "Ananya", "David", "Emma", "Arjun", "Frank",
			"Aisha", "George", "Hannah", "Amit", "Iris", "Jack");

	List<String> namesUpperCase = names.stream().map(String::toUpperCase).collect(Collectors.toList());
	
	System.out.println("Names Uppercase");
	for(String name : namesUpperCase) {
		System.out.println(name);
	}
	
	long countOfStringStartWithA = namesUpperCase.stream().filter(name -> name.startsWith("A")).count();
	System.out.println("Count of Names Start with A : "  + countOfStringStartWithA);
}
}




