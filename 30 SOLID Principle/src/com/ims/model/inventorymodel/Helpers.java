package com.ims.model.inventorymodel;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Helpers {

	// ────────────────────────────────────────────────
	// Integer validation
	// ────────────────────────────────────────────────

	public static int validateInt(Scanner scanner) {
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid integer: ");
				scanner.next();
				continue;
			}
			int value = scanner.nextInt();
			scanner.nextLine();
			return value;
		}
	}

	public static int validateIntPositive(Scanner scanner) {
		while (true) {
			int value = validateInt(scanner);
			if (value <= 0) {
				System.out.print("Value must be positive (> 0): ");
				continue;
			}
			return value;
		}
	}

	public static int validateIntNonNegative(Scanner scanner) {
		while (true) {
			int value = validateInt(scanner);
			if (value < 0) { // was < 1 — fixed
				System.out.print("Value cannot be negative: ");
				continue;
			}
			return value;
		}
	}

	public static int validateIntRange(Scanner scanner, int min, int max) {
		while (true) {
			int value = validateInt(scanner);
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

	public static double validateDouble(Scanner scanner) {
		while (true) {
			if (!scanner.hasNextDouble()) {
				System.out.print("Please enter a valid decimal number: ");
				scanner.next();
				continue;
			}
			double value = scanner.nextDouble();
			scanner.nextLine();
			return value;
		}
	}

	public static double validateDoublePositive(Scanner scanner) {
		while (true) {
			double value = validateDouble(scanner);
			if (value <= 0) {
				System.out.print("Amount must be positive (> 0): ");
				continue;
			}
			return value;
		}
	}

	public static double validateDoubleNonNegative(Scanner scanner) {
		while (true) {
			double value = validateDouble(scanner);
			if (value < 0) {
				System.out.print("Amount cannot be negative: ");
				continue;
			}
			return value;
		}
	}

	// ────────────────────────────────────────────────
	// String validation
	// ────────────────────────────────────────────────

	public static String validateStringNonEmpty(Scanner scanner) {
		while (true) {
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.print("Input cannot be empty. Please enter a value: ");
				continue;
			}
			return input;
		}
	}

	public static String validateStringLettersOnly(Scanner scanner) {
		while (true) {
			String input = validateStringNonEmpty(scanner);
			if (!Pattern.matches("[a-zA-Z\\s\\-']+", input)) {
				System.out.print("Please enter letters only (spaces, hyphens and apostrophes allowed): ");
				continue;
			}
			return input;
		}
	}

	public static String validateName(Scanner scanner) {
		while (true) {
			String input = validateStringLettersOnly(scanner);

			if (input.length() < 3) {
				System.out.println("Please enter atleast 3 letters");
				continue;
			}
			return input;
		}

	}

	public static int validateThreshold(Scanner scanner) {
		return validateIntPositive(scanner);
	}

	public static int validateStock(Scanner scanner, int threshold) {
		while (true) {
			int input = validateIntPositive(scanner);

			if (input < threshold) {
				System.out.println("Quantity can't be less then threshold.");
				continue;
			}
			return input;
		}
	}

	public static double validatePrice(Scanner scanner) {
		return validateDoublePositive(scanner);
	}
}