package com.gurpreet.encapsulation.models;

public class BankAccount {
	private static long counter = 100000L;
	private final String accountNumber;
	private String accountHolderName;
	private double balance;

	public BankAccount(String name, double initialBalance) {

		if (name == null || name.trim().isEmpty()) {
			System.out.println("Invalid name - using default: Guest");
			this.accountHolderName = "Guest";
		} else {
			this.accountHolderName = name;
		}

		if (initialBalance < 0) {
			System.out.println("Initial balance cannot be negative - set to 0");
			this.balance = 0;
		} else {
			this.balance = initialBalance;
		}
		this.accountNumber = generateAccountNumber();
	}

	private String generateAccountNumber() {
		return "AC" + ++counter;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String newName) {
		if (newName == null || newName.trim().isEmpty()) {
			System.out.println("Name cannot be empty - change rejected");
		} else {
			this.accountHolderName = newName;
			System.out.println("Account holder name updated");
		}
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			System.out.println("Deposit amount must be positive - transaction rejected");
		} else {
			balance += amount;
			System.out.println("Deposit successful");
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			System.out.println("Withdrawal amount must be positive - transaction rejected");
		} else if (amount > balance) {
			System.out.println("Insufficient balance - withdrawal rejected");
		} else {
			balance -= amount;
			System.out.println("Withdrawal successful");
		}
	}

	public double getInterest() {
		double interest = calculateInterest();
		return interest;
	}

	// Internal method - not exposed
	private double calculateInterest() {
		return balance * 0.05;
	}
}
