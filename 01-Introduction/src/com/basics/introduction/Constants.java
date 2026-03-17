package com.basics.introduction;


public class Constants {
	public static void main(String[] args) {
		final double PI = 3.14;
		final int MAX_VALUE = 100;
		final int MIN_VALUE = 1;

		System.out.println(PI);
		System.out.println(MAX_VALUE);
		System.out.println(MIN_VALUE);

		//MIN_VALUE = 200;
		System.out.println(MIN_VALUE);

		/*
		 * Exception in thread "main" java.lang.Error: Unresolved compilation problem:
		 * The final local variable MIN_VALUE cannot be assigned. It must be blank and
		 * not using a compound assignment
		 * 
		 * at com.basics.introduction.Constants.main(Constants.java:23)
		 */
		
		/*
		 * 
		 */

		// WARN: final variables cannot be reassigned — will throw compile error
		// final int TARGET_SCORE = 20;
		// TARGET_SCORE = 50;  ← ❌ this breaks

	}

}
