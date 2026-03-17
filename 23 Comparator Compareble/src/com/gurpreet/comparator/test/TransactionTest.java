package com.gurpreet.comparator.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.comparator.comparators.AmountIdComparator;
import com.gurpreet.comparator.models.Transaction;

public class TransactionTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Transaction> transactions = new ArrayList<>();

		System.out.println("=========================================");
		System.out.println("      Transaction List ");
		System.out.println("=========================================");

		int numberOfTransactions = getPositiveInteger(scanner, "How many transactions would you like to enter? ");

		for (int i = 1; i <= numberOfTransactions; i++) {
			System.out.println("\nTransaction #" + i + ":");

			int id = getValidId(scanner, "Enter transaction ID: ");
			double amount = getPositiveDouble(scanner, "Enter amount (in ₹): ");

			transactions.add(new Transaction(id, amount));
		}

		// Display before sorting
		System.out.println("\nBefore sorting (insertion order):");
		printTransactions(transactions);

		// Sort using natural ordering (assumed to be by amount)
		Collections.sort(transactions, new AmountIdComparator());

		// Display after sorting
		System.out.println("\nAfter sorting (by amount ascending):");
		printTransactions(transactions);

		scanner.close();
	}

	// ────────────────────────────────────────────────
	// Input Validation Helpers
	// ────────────────────────────────────────────────

	private static int getPositiveInteger(Scanner sc, String prompt) {
		int value;
		do {
			System.out.print(prompt);
			while (!sc.hasNextInt()) {
				System.out.print("Please enter a valid positive integer: ");
				sc.next();
			}
			value = sc.nextInt();
			sc.nextLine(); // consume newline
			if (value <= 0) {
				System.out.println("Value must be greater than zero.");
			}
		} while (value <= 0);
		return value;
	}

	private static int getValidId(Scanner sc, String prompt) {
		int value;
		do {
			System.out.print(prompt);
			while (!sc.hasNextInt()) {
				System.out.print("Please enter a valid ID (positive number): ");
				sc.next();
			}
			value = sc.nextInt();
			sc.nextLine();
			if (value <= 0) {
				System.out.println("ID must be a positive number.");
			}
		} while (value <= 0);
		return value;
	}

	private static double getPositiveDouble(Scanner sc, String prompt) {
		double value;
		do {
			System.out.print(prompt);
			while (!sc.hasNextDouble()) {
				System.out.print("Please enter a valid amount: ");
				sc.next();
			}
			value = sc.nextDouble();
			sc.nextLine();
			if (value <= 0) {
				System.out.println("Amount must be greater than zero.");
			}
		} while (value <= 0);
		return value;
	}

	// ────────────────────────────────────────────────
	// Display Helper
	// ────────────────────────────────────────────────

	private static void printTransactions(List<Transaction> list) {
		System.out.println("ID                  Amount (₹)");
		System.out.println("-----------------------------------");
		for (Transaction t : list) {
			System.out.printf("%-18d %,12.2f%n", t.getId(), t.getAmount());
		}
		System.out.println();
	}

	/*
	 * transactions.add(new Transaction(1005, 2500.75)); transactions.add(new
	 * Transaction(1001, 4500.00)); transactions.add(new Transaction(1008,
	 * 1200.50)); transactions.add(new Transaction(1003, 4500.00));
	 * transactions.add(new Transaction(1007, 800.25)); transactions.add(new
	 * Transaction(1002, 9800.00)); transactions.add(new Transaction(1004,
	 * 4500.00));
	 */
}