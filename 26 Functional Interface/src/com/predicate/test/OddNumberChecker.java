package com.predicate.test;

import java.util.function.Predicate;

public class OddNumberChecker {

	public static void main(String[] args) {

		// predicate interface 
		Predicate<Integer> oddNumber = num -> num % 2 != 0;
		
		
		for(int i = 1 ; i <= 10 ; i++) {
			if(oddNumber.test(i)) {
				System.out.println( i + " is Odd");
			}
		}
	}

}
