package com.swabhav.evaluation.poc.basics;

/**
 * POC: Basic: Write a standard HelloWorld class. Try compiling it with a
 * filename that doesn't exactly match the class name (captilization). Prove
 * what the compiler says.
 */
public class BasicHelloWorldPOC {

	public static void main(String[] args) {
		System.out.println("Hello World");

	}
}

/*
 * Error: Could not find or load main class
 * com.swabhav.evaluation.poc.basics.BasicHelloWorldPOC Caused by:
 * java.lang.ClassNotFoundException:
 * com.swabhav.evaluation.poc.basics.BasicHelloWorldPOC
 */

/*
 * 1. if the file name and class name did not match class loader ill not able to
 * lead the class. and give the exception of ClassNotFoundException
 */
