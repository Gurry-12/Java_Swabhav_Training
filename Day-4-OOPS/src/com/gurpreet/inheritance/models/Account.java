package com.gurpreet.inheritance.models;

public class Account {

	// fields
	private long counter = 100000L;
	private long accountNumber;
	private String accountHolderName;
	private double balance;
		
	// constructor 
	public Account(String accountHolderName, double balance) {
		
		// validation for name check
		if(accountHolderName == null || accountHolderName.isEmpty()) {
			System.out.println("Please Enter valid Name.");
			return;
		}
		
		if(balance < 0 ) {
			System.out.println("Balance can not be negative.");
			return;
		}
		
		this.accountNumber = ++counter;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		System.out.println("Account created successfully.");
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) {
		
		if(amount <= 0) {
			System.out.println("Deposit amount can not be negative.");
			return;
		}
		
		balance += amount;
		System.out.println("Amount deposit successfully.");
	}
	
	public void withdraw(double amount) {
		
		if(amount <= 0) {
			System.out.println("Withdraw amount can not be negative.");
			return;
		}
		
		balance -= amount;
		System.out.println("Amount withdrawal successfully.");
	}
	
	// display method
	public void displayDetails() {
		System.out.println("Here are the details: ");
		System.out.println("Account number: " + accountNumber);
		System.out.println("Account holder name: " + accountHolderName);
		System.out.println("Balance: " + balance);
		
	}
	
}
