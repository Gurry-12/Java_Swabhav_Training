package com.swabhav.evaluation.poc.basics;

/**
 * POC: Intermediate: Create two completely separate classes in this single
 * file. Give both of them a `public static void main(String[] args)` method.
 * Compile and prove how you can choose which one executes.
 */
public class MultipleMainsTest {
	public static void main(String[] args) {
		System.out.println("Main of Class A");
	}
}

class SecondMain {
	public static void main(String[] args) {
        System.out.println("Main of Class B");
    }
}

/*
 * We CAN have multiple classes in one .java file
 * 
 * But ONLY ONE can be public
 */
