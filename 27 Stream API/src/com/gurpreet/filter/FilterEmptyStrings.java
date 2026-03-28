package com.gurpreet.filter;

import java.util.List;
import java.util.stream.Collectors;

public class FilterEmptyStrings {
	public static void main(String[] args) {

		List<String> stringList = List.of("Hello", "", "World", "   ", "Java", "Programming", "", "Data", "   ",
				"Stream", "Filter", "Example", "");

		List<String> filterStrings = stringList.stream().filter(str -> str != null && !str.trim().isEmpty())
				.collect(Collectors.toList());
		
		System.out.println("Original List count     : " + stringList.size());
        System.out.println("Filtered List count     : " + filterStrings.size());
        System.out.println("\nFiltered Strings:");
		
		for(String s : filterStrings) {
			System.out.println(s);
		}
	}
}
