package com.basics.introduction;

import java.util.Scanner;


public class IfElseStatements {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a Number: ");
		int number = scanner.nextInt();

		if (number > 0) {
			System.out.println(number + " is Positive");
		} else if (number < 0) {
			System.out.println(number + " is Negative");
		} else {
			System.out.println(number + " is Zero");
		}

		if (number % 5 == 0) {
			System.out.println(number + " is Divisible by 5");
		} else {
			System.out.println(number + " is not Divisible by 5");
		}

		if (number % 2 == 0) {
			System.out.println(number + " is Even");
		} else {
			System.out.println(number + " is Odd");
		}
		
		scanner.close();
	}

}
