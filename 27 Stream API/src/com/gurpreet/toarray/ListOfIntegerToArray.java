package com.gurpreet.toarray;

import java.util.Arrays;
import java.util.List;

public class ListOfIntegerToArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> words = List.of(
	            "hello", "world", "java", "stream", "programming",
	            "gurpreet", "jaipur", "rajasthan", "india", "filter"
	        );
		
		String[] stringArray =  words.stream().toArray(String[]::new);
		
		System.out.println(Arrays.toString(stringArray));
	}

}
