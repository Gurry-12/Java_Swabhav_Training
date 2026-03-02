package com.gurpreet.constructor.test;

import java.util.Scanner;
import com.gurpreet.constructor.models.BankAccount;
import com.gurpreet.constructor.models.PremiumAccount;
import com.gurpreet.helpers.Helpers;

/**
 * Test for Question 1: Banking Domain - Constructor Validation
 */
public class BankAccountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BankAccount account = null;

		System.out.println("========================================");
		System.out.println("     BANKING SYSTEM - CONSTRUCTOR DEMO");
		System.out.println("========================================\n");

		boolean running = true;
		while (running) {
			displayMenu();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				account = createRegularAccount(scanner);
				break;

			case 2:
				account = createPremiumAccount(scanner);
				break;

			case 3:
				if (account != null) {
					deposit(scanner, account);
				} else {
					System.out.println("Please create an account first.");
				}
				break;

			case 4:
				if (account != null) {
					withdraw(scanner, account);
				} else {
					System.out.println("Please create an account first.");
				}
				break;

			case 5:
				if (account != null) {
					account.displayAccountInfo();
				} else {
					System.out.println("No account exists.");
				}
				break;

			case 6:
				demonstrateValidation();
				break;

			case 7:
				running = false;
				System.out.println("Thank you for using Banking System.");
				break;

			default:
				System.out.println("Invalid choice. Please enter 1-7.");
			}

			System.out.println();
		}

		scanner.close();
	}

	private static void displayMenu() {
		System.out.println("-----------------------------");
		System.out.println("     Banking Menu");
		System.out.println("1. Create Regular Account");
		System.out.println("2. Create Premium Account");
		System.out.println("3. Deposit Money");
		System.out.println("4. Withdraw Money");
		System.out.println("5. Display Account Info");
		System.out.println("6. Demonstrate Constructor Validation");
		System.out.println("7. Exit");
		System.out.println("-----------------------------");
	}

	private static BankAccount createRegularAccount(Scanner scanner) {
		System.out.println("\n--- Create Regular Account ---");
		
		System.out.print("Enter account number: ");
		String accNum = Helpers.validateString(scanner);

		System.out.print("Enter account holder name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter initial balance (or 0 for default): ");
		double balance = Helpers.validateDouble(scanner);
		scanner.nextLine();

		try {
			BankAccount account;
			if (balance == 0) {
				// Constructor overloading - defaults to 0
				account = new BankAccount(accNum, name);
			} else {
				account = new BankAccount(accNum, name, balance);
			}
			System.out.println("✓ Regular account created successfully!");
			return account;
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
			return null;
		}
	}

	private static BankAccount createPremiumAccount(Scanner scanner) {
		System.out.println("\n--- Create Premium Account ---");
		
		System.out.print("Enter account number: ");
		String accNum = Helpers.validateString(scanner);

		System.out.print("Enter account holder name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter initial balance: ");
		double balance = Helpers.validateDouble(scanner);

		System.out.print("Enter welcome bonus amount: ");
		double bonus = Helpers.validateDouble(scanner);

		System.out.print("Enter membership level (Gold/Platinum/Diamond): ");
		String level = scanner.next();
		scanner.nextLine();

		try {
			PremiumAccount account = new PremiumAccount(accNum, name, balance, bonus, level);
			System.out.println("✓ Premium account created successfully!");
			return account;
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
			return null;
		}
	}

	private static void deposit(Scanner scanner, BankAccount account) {
		System.out.print("Enter amount to deposit: ");
		double amount = Helpers.validateDouble(scanner);
		scanner.nextLine();

		try {
			account.deposit(amount);
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
		}
	}

	private static void withdraw(Scanner scanner, BankAccount account) {
		System.out.print("Enter amount to withdraw: ");
		double amount = Helpers.validateDouble(scanner);
		scanner.nextLine();

		try {
			account.withdraw(amount);
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
		}
	}

	private static void demonstrateValidation() {
		System.out.println("\n========================================");
		System.out.println("  CONSTRUCTOR VALIDATION DEMONSTRATION");
		System.out.println("========================================\n");

		// Test 1: Negative balance
		System.out.println("Test 1: Trying to create account with negative balance...");
		try {
			BankAccount acc1 = new BankAccount("ACC001", "John Doe", -1000);
			System.out.println("✗ Should have failed!");
		} catch (IllegalArgumentException e) {
			System.out.println("✓ Correctly rejected: " + e.getMessage());
		}

		// Test 2: Null account number
		System.out.println("\nTest 2: Trying to create account with null account number...");
		try {
			BankAccount acc2 = new BankAccount(null, "Jane Doe", 5000);
			System.out.println("✗ Should have failed!");
		} catch (IllegalArgumentException e) {
			System.out.println("✓ Correctly rejected: " + e.getMessage());
		}

		// Test 3: Empty name
		System.out.println("\nTest 3: Trying to create account with empty name...");
		try {
			BankAccount acc3 = new BankAccount("ACC003", "", 5000);
			System.out.println("✗ Should have failed!");
		} catch (IllegalArgumentException e) {
			System.out.println("✓ Correctly rejected: " + e.getMessage());
		}

		// Test 4: Valid account
		System.out.println("\nTest 4: Creating valid account...");
		try {
			BankAccount acc4 = new BankAccount("ACC004", "Valid User", 10000);
			System.out.println("✓ Account created successfully!");
			acc4.displayAccountInfo();
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Unexpected error: " + e.getMessage());
		}

		System.out.println("\n========================================");
	}

}
