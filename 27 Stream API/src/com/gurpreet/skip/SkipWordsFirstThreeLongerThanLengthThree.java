package com.gurpreet.skip;

import java.util.List;

public class SkipWordsFirstThreeLongerThanLengthThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> words = List.of("hello", "cat", "programming", "java", "stream", "code", "india", "rajasthan",
				"guru", "filter");

		words.stream().filter(w -> w.length() > 3).skip(3).forEach(System.out::println);
	}

}
