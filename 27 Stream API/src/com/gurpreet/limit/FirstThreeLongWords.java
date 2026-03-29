package com.gurpreet.limit;

import java.util.List;

public class FirstThreeLongWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> words = List.of("hello", "programming", "java", "stream", "code", "india", "rajasthan", "computer");

        words.stream()
             .sorted((a, b) -> b.length() - a.length())
             .limit(3)
             .forEach(System.out::println);
	}

}
