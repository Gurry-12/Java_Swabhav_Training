package com.gurpreet.inheritance.test;

import java.util.Scanner;
import com.gurpreet.inheritance.models.Account;
import com.gurpreet.inheritance.models.CurrentAccount;
import com.gurpreet.inheritance.models.SavingAccount;

public class BankApp {
	

	public static void main(String[] args) {
		
		 Scanner scanner = new Scanner(System.in);
		 Account account = null;
		 
		System.out.println("Welcome to the Digital Banking System");
		boolean isRun = true;
		do {
			// display menu
			displayMainMenu();

			System.out.println("Enter your choice (1-6): ");

			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {

			case 1:
				account = createSavingAccount(scanner, account);
				break;

			case 2:
				account = createCurrentAccount(scanner, account);
				break;

			case 3:
				depositAmount(scanner, account);
				break;

			case 4:
				withdrawAmount(scanner, account);
				break;

			case 5:
				displayAccountDetails(account);
				break;

			case 6:
				isRun = false;
				System.out.println("Thank you visiting");
				break;

			default:
				System.out.println("Please enter valid input: 1 - 6 ");
			}
		} while (isRun);

		scanner.close();
	}

	private static void displayAccountDetails(Account account) {
		if (account == null) {
			System.out.println("No active account. Please create one first.");
			return;
		}
		account.displayDetails();

	}

	private static void withdrawAmount(Scanner scanner, Account account) {
		System.out.println("Enter amount to withdraw: ");
		double amount = scanner.nextDouble();

		account.withdraw(amount);
	}

	private static void depositAmount(Scanner scanner, Account account) {
		System.out.println("Enter amount to deposit: ");
		double amount = scanner.nextDouble();

		account.deposit(amount);

	}

	private static Account createCurrentAccount(Scanner scanner, Account account) {
		System.out.println("Enter account holder name: ");
		String holderName = scanner.nextLine().trim();

		System.out.println("Enter initial balance: ");
		double balance = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("Enter over draft limit: ");
		double overDraftLimit = scanner.nextDouble();
		scanner.nextLine();

		if (overDraftLimit < 0) {
			System.out.println("Over draft limit can not be negative. ");
			return null;
		}

		account = new CurrentAccount(holderName, balance, overDraftLimit);
		return account;

	}

	private static Account createSavingAccount(Scanner scanner, Account account) {
		System.out.println("Enter account holder name: ");
		String holderName = scanner.nextLine().trim();

		System.out.println("Enter initial balance: ");
		double balance = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("Enter minimum balance: ");
		double minimumBalance = scanner.nextDouble();
		scanner.nextLine();

		if (minimumBalance < 0) {
			System.out.println("Minimum balance can not be negative. ");
			return null;
		}

		account = new SavingAccount(holderName, balance, minimumBalance);
		return account;

	}

	private static void displayMainMenu() {
		System.out.println("Mini Banking System");
		System.out.println("───────────────────────────────");
		System.out.println("1. Create New Savings Account");
		System.out.println("2. Create New Current Account");
		System.out.println("3. Deposit Money");
		System.out.println("4. Withdraw Money");
		System.out.println("5. Display Account Details");
		System.out.println("6. Exit");
	}
}
