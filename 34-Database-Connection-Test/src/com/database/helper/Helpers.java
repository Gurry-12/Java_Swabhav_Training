package com.database.helper;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Helpers {

	// ────────────────────────────────────────────────
	// Integer validation
	// ────────────────────────────────────────────────

	public static int validateIntPositive(Scanner scanner) {
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid integer: ");
				scanner.next();
				continue;
			}
			int value = scanner.nextInt();
			scanner.nextLine();
			if (value <= 0) {
				System.out.print("Value must be positive (> 0): ");
				continue;
			}
			return value;
		}
	}
	
	public static int validateIntRange(Scanner scanner, int min, int max) {
		while (true) {
			int value = validateIntPositive(scanner);
			if (value < min || value > max) {
				System.out.printf("Please enter a number between %d and %d: ", min, max);
				continue;
			}
			return value;
		}
	}

	// ────────────────────────────────────────────────
	// Double validation
	// ────────────────────────────────────────────────

	public static double validateDoublePositive(Scanner scanner) {
		while (true) {
			if (!scanner.hasNextDouble()) {
				System.out.print("Please enter a valid decimal number: ");
				scanner.next();
				continue;
			}
			double value = scanner.nextDouble();
			scanner.nextLine();
			if (value <= 0) {
				System.out.print("Amount must be positive (> 0): ");
				continue;
			}
			return value;
		}
	}
	
	public static double validateDoubleRange(Scanner scanner, double min, double max) {
		while (true) {
			double value = validateDoublePositive(scanner);
			if (value < min || value > max) {
				System.out.printf("Please enter a number between %d and %d: ", min, max);
				continue;
			}
			return value;
		}
	}

	// ────────────────────────────────────────────────
	// String validation
	// ────────────────────────────────────────────────


	public static String validateStringLettersOnly(Scanner scanner) {
		while (true) {
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.print("Input cannot be empty. Please enter a value: ");
				continue;
			}
			if (!Pattern.matches("[a-zA-Z\\s\\-']+", input)) {
				System.out.print("Please enter letters only (spaces, hyphens and apostrophes allowed): ");
				continue;
			}
			return input;
		}
	}

}