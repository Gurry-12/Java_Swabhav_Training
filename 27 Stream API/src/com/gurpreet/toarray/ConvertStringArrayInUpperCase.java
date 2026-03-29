package com.gurpreet.toarray;

import java.util.Arrays;

public class ConvertStringArrayInUpperCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {
	            "hello", "world", "java", "stream", "programming",
	            "gurpreet", "jaipur", "rajasthan", "india", "filter"
		};
		
		Arrays.stream(words).map(String::toUpperCase).forEach(System.out::println);
	}

}
