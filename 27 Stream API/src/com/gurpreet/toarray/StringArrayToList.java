package com.gurpreet.toarray;

import java.util.Arrays;
import java.util.List;

public class StringArrayToList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {
	            "hello", "world", "java", "stream", "programming",
	            "gurpreet", "jaipur", "rajasthan", "india", "filter"
		};
		
		List<String> stringList = Arrays.stream(words).toList();
		
		stringList.forEach(System.out::println);
	}

}
