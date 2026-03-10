package com.gurpreet.oopsassignment.helpers;

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
                scanner.next(); // discard invalid input
                continue;
            }
            int value = scanner.nextInt();
            scanner.nextLine(); // consume newline
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
            if (value < 0) {
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
                scanner.next(); // discard invalid
                continue;
            }
            double value = scanner.nextDouble();
            scanner.nextLine(); // consume newline
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
            if (input.isEmpty() || input.isBlank()) {
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

    public static String validateStringAlphanumeric(Scanner scanner) {
        while (true) {
            String input = validateStringNonEmpty(scanner);
            if (!Pattern.matches("[a-zA-Z0-9\\s\\-']+", input)) {
                System.out.print("Only letters, numbers, spaces, hyphens and apostrophes allowed: ");
                continue;
            }
            return input;
        }
    }

    // ────────────────────────────────────────────────
    // Utility / convenience methods
    // ────────────────────────────────────────────────

    /**
     * Pauses execution until user presses Enter
     */
    public static void pressEnterToContinue(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Simple yes/no confirmation
     */
    public static boolean confirmYesNo(Scanner scanner, String question) {
        while (true) {
            System.out.print(question + " (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                return true;
            }
            if (answer.equals("no") || answer.equals("n")) {
                return false;
            }
            System.out.println("Please answer yes or no.");
        }
    }
}