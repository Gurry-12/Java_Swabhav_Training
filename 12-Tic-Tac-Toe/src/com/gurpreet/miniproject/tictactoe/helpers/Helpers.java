package com.gurpreet.miniproject.tictactoe.helpers;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Helpers {
	
	
	public static int validateInt(Scanner scanner) {
		int input;
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.println("Enter a valid integer Input ");
				scanner.next();
				continue;
			}
			input = scanner.nextInt();
			
			if (input < 0 && input > 9) {
				System.out.println("Enter a valid Input [1-9]");
				continue;
			} 
			break;
		}
		return input;
	}

	public static double validateDouble(Scanner scanner) {
		double input;
		while (true) {
			if (!scanner.hasNextDouble()) {
				System.out.println("Enter a valid Decimal Input ");
				scanner.next();
				continue;
			}
			input = scanner.nextDouble();
			break;
		}
		return input;
	}

	public static String validateString(Scanner scanner) {
		String input;
		while (true) {
			
			input = scanner.nextLine().trim();
			
			if(input.isBlank() || Pattern.matches("\\s*$", input)) {
				System.out.println("Input cannot be empty.");
				continue;
			}
			
			if (!Pattern.matches("[a-zA-Z0-9].*",input)) {
				System.out.println("Enter a valid String Input ");
				continue;
			}
			
			break;
		}
		return input;
	}

	public static double validateDoubleNoNegative(Scanner scanner) {
		double input;
		while (true) {
			if (!scanner.hasNextDouble()) {
				System.out.println("Enter a valid Decimal Input ");
				scanner.next();
				continue;
			}
			
			input = scanner.nextDouble();
			
			if(input < 0) {
				System.out.println("Amount can not be negative");
				continue;
			}
			break;
		}
		return input;
	}

}
