package com.swabhav.evaluation.poc.controlflow;

import java.util.Scanner;

/**
 * POC: Basic: Accept three numbers from the command line. Find and print the
 * maximum of the three numbers using ONLY the ternary operator (`? :`) without
 * any `if` statements.
 */
public class TernaryMaxPOC {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter  3 no. ");
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		
		System.out.println((a > b && a > c) ? a : (b > c) ? b : c);
		scanner.close();
	}
}
