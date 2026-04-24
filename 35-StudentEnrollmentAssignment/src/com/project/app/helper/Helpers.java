package com.project.app.helper;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Helpers {

	// ────────────────────────────────────────────────
	// Integer validation
	// ────────────────────────────────────────────────

	public static int validateIntPositive(Scanner scanner, String prompt) {
		System.out.println(prompt);
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.println("Please enter a valid integer: ");
				scanner.next();
				continue;
			}
			int value = scanner.nextInt();
			scanner.nextLine();
			if (value <= 0) {
				System.out.println("Value must be positive (> 0): ");
				continue;
			}
			return value;
		}
	}

	public static int validateIntRange(Scanner scanner, int min, int max, String prompt) {
		while (true) {
			int value = validateIntPositive(scanner, prompt);
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

	public static double validateDoublePositive(Scanner scanner, String prompt) {
		System.out.println(prompt);
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

	public static double validateDoubleRange(Scanner scanner, double min, double max, String prompt) {
		while (true) {
			double value = validateDoublePositive(scanner, prompt);
			if (value < min || value > max) {
				System.out.printf("Please enter a number between %f and %f: ", min, max);
				continue;
			}
			return value;
		}
	}

	// ────────────────────────────────────────────────
	// String validation
	// ────────────────────────────────────────────────

	public static String validateString(Scanner scanner, String prompt) {
		System.out.println(prompt);
		while (true) {
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println("Input cannot be empty. Please enter a value: ");
				continue;
			}
			if (!Pattern.matches("[a-zA-Z\\s\\-']+", input)) {
				System.out.println("Please enter letters only (spaces, hyphens and apostrophes allowed): ");
				continue;
			}
			return input;
		}
	}

}