package com.swabhav.evaluation.poc.controlflow;

/**
 * POC: Advanced: Write a method that infinitely calls itself. Wrap the call in
 * a try-catch block catching `StackOverflowError`. Use a counter passed as a
 * parameter to find out exactly how deep the call stack can get on your JVM.
 * 
 * 
 */


public class CallStackTracerTest {
	public static int count = 0;
	public static int method(int c) {
		count = c;
		return method(c + 1 );
	}
	public static void main(String[] args) {
		// Implement the Proof of Concept here

		try {
			 int n = method(1);
		}
		catch(StackOverflowError e) {
			System.out.println(e.getMessage() + " " + count);
		}
		
	}
}
