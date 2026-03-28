package com.gurpreet.map;

import java.util.List;

public class ConvertStringsToUpperCase {

	public static void main(String[] args) {
		
		List<String> words = List.of(
	            "hello", "world", "java", "stream", "programming",
	            "gurpreet", "jaipur", "rajasthan", "india", "filter"
	        );
		
		words.stream().map(String::toUpperCase).forEach(System.out::println);

	}

}
