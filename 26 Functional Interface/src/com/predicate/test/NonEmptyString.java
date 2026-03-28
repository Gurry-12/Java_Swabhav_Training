package com.predicate.test;

import java.util.function.Predicate;

public class NonEmptyString {
	public static void main(String[] args) {
		
		
		// predicate 
		Predicate<String> nonEmpty = str -> str != null && !str.trim().isEmpty();
		
		String[] stringArray = {"", "Java", null, " "};
		
		for (String s : stringArray) {
            if (nonEmpty.test(s)) {
                System.out.println("[" + s + "]");
            }
        }
		
	}
}
