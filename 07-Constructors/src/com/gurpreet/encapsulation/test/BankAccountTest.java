package com.gurpreet.encapsulation.test;

import java.util.Scanner;

import com.gurpreet.encapsulation.models.BankAccount;
import com.gurpreet.helpers.Helpers;

public class BankAccountTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Digital Banking System");

		System.out.print("Enter account holder name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter initial balance: ");
		double initialBalance = Helpers.validateDouble(scanner);
		scanner.nextLine();
		
		BankAccount account = new BankAccount(name, initialBalance);
		System.out.println("Account created. Account Number: " + account.getAccountNumber());

		boolean running = true;
		while (running) {
			System.out.println("\n=== Banking Menu ===");
			System.out.println("1. Deposit Money");
			System.out.println("2. Withdraw Money");
			System.out.println("3. Check Balance");
			System.out.println("4. Change Account Holder Name");
			System.out.println("5. Exit");
			System.out.print("Choose option (1-5): ");

			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();
			
			if (choice == 1) {
				System.out.print("Enter amount to deposit: ");
				double amount = Helpers.validateDouble(scanner);
				scanner.nextLine();
				account.deposit(amount);

			} else if (choice == 2) {
				System.out.print("Enter amount to withdraw: ");
				double amount = Helpers.validateDouble(scanner);
				scanner.nextLine();
				account.withdraw(amount);
			} else if (choice == 3) {
				System.out.println("Current balance: " + account.getBalance());
			} else if (choice == 4) {
				scanner.nextLine(); // clear buffer
				System.out.print("Enter new account holder name: ");
				String newName = Helpers.validateString(scanner);
				account.setAccountHolderName(newName);
			} else if (choice == 5) {
				running = false;
			} else {
				System.out.println("Invalid option. Please choose 1 to 5.");
			}
		}

		System.out.println("Thank you for using the Digital Banking System.");
		scanner.close();
	}
}
