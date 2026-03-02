package com.gurpreet.abstractclass.test;

import java.util.Scanner;

import com.gurpreet.abstractclass.models.CreditCardPayment;
import com.gurpreet.abstractclass.models.Payment;
import com.gurpreet.abstractclass.models.UPIPayment;
import com.gurpreet.abstractclass.models.WalletPayment;
import com.gurpreet.helpers.Helpers;

public class PaymentDemo {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int paymentCount = 0;

		System.out.println("-----------------------------------");
		System.out.println(" Welcome to the Payment World ");
		System.out.println();

		System.out.println("Enter how many payments do you want to perform (maximum):");
		int maxPayment = Helpers.validateInt(scanner);

		Payment[] payments = new Payment[maxPayment];

		boolean isRunning = true;

		while (isRunning) {

			displayDashboard();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);

			switch (choice) {

			case 1:
				if (paymentCount >= maxPayment) {
					System.out.println("Maximum number of payments reached.");
					break;
				}
				paymentCreditCard(scanner, payments, paymentCount);
				paymentCount++;
				break;

			case 2:
				if (paymentCount >= maxPayment) {
					System.out.println("Maximum number of payments reached.");
					break;
				}
				paymentUPI(scanner, payments, paymentCount);
				paymentCount++;
				break;

			case 3:
				if (paymentCount >= maxPayment) {
					System.out.println("Maximum number of payments reached.");
					break;
				}
				paymentWallet(scanner, payments, paymentCount);
				paymentCount++;
				break;

			case 4:
				displayAllReceipt(payments, paymentCount);
				break;

			case 5:
				isRunning = false;
				System.out.println("Thank you for visiting.");
				break;

			default:
				System.out.println("Please enter a valid choice (1-5).");
			}
		}

		scanner.close();
	}

	private static void displayDashboard() {
		System.out.println("\n-----------------------------");
		System.out.println("1: Credit Card Payment");
		System.out.println("2: UPI Payment");
		System.out.println("3: Wallet Payment");
		System.out.println("4: Display all receipts");
		System.out.println("5: Exit");
		System.out.println("-----------------------------");
	}

	private static void paymentCreditCard(Scanner scanner, Payment[] payments, int index) {
		System.out.print("Enter amount for Credit Card payment: ");
		double amount = Helpers.validateDouble(scanner); // assuming you have this method

		if (amount <= 0) {
			System.out.println("Amount must be greater than 0. Payment cancelled.");
			return;
		}

		payments[index] = new CreditCardPayment(amount);
		System.out.println("Credit Card payment added successfully.");
	}

	private static void paymentUPI(Scanner scanner, Payment[] payments, int index) {
		System.out.print("Enter amount for UPI payment: ");
		double amount = Helpers.validateDouble(scanner);

		if (amount <= 0) {
			System.out.println("Amount must be greater than 0. Payment cancelled.");
			return;
		}

		payments[index] = new UPIPayment(amount);
		System.out.println("UPI payment added successfully.");
	}

	private static void paymentWallet(Scanner scanner, Payment[] payments, int index) {
		System.out.print("Enter amount for Wallet payment: ");
		double amount = Helpers.validateDouble(scanner);

		if (amount <= 0) {
			System.out.println("Amount must be greater than 0. Payment cancelled.");
			return;
		}

		payments[index] = new WalletPayment(amount);
		System.out.println("Wallet payment added successfully.");
	}

	private static void displayAllReceipt(Payment[] payments, int count) {
		if (count == 0) {
			System.out.println("No payments have been added yet.");
			return;
		}

		System.out.println("\n--- All Payment Receipts ---");
		for (int i = 0; i < count; i++) {
			Payment p = payments[i];
			if (!p.validateAmount()) {
				System.out.println("Invalid payment at index " + i + " (amount <= 0)");
				return;
			}
			p.processPayment();
			p.generateReceipt();
			System.out.println("-----------------------------");
		}
	}
}