package com.gurpreet.oopsassignment.models;


public abstract class Account {
	private static long accountCounter = 0;
	private String accountNumber;
	private String accountHolderName;
	protected double balance;

	// Constructor with chaining support and validation
	public Account(String accountHolderName, double initialBalance, String prefix) {

		if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
			throw new IllegalArgumentException("Account holder name must not be null or empty.");
		}
		if (initialBalance < 0) {
			throw new IllegalArgumentException("Initial balance cannot be negative.");
		}
		this.accountNumber = prefix + String.format("%04d", ++accountCounter);
		;
		this.accountHolderName = accountHolderName;
		this.balance = initialBalance;
	}

	// Getters for read-only access
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	// Concrete method for common behavior with validation
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive.");
		}
		this.balance += amount;
	}

	// Abstract method for polymorphic withdrawal with type-specific rules
	public abstract void withdraw(double amount);

	// Common display method for polymorphism
	public void displayDetails() {
		System.out.println(
				"Account Number: " + accountNumber + ", Holder: " + accountHolderName + ", Balance: $" + balance);
	}
}
