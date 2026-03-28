package com.function.test;

import java.util.function.Function;

public class StringLengthCalculator {

	public static void main(String[] args) {
		
		 Function<String, Integer> stringLength = str -> str.length();
		 
		 System.out.println(stringLength.apply("Gurpreet"));
		 System.out.println(stringLength.apply("Singh"));
		 System.out.println(stringLength.apply("Rohit")); 
		 
	}
	
}