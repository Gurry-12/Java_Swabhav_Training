package com.gurpreet.model;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
    	if (initialBalance < 0) {
        	throw new IllegalArgumentException("Invalid withdrawal amount");
           
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
    	if (amount < 0) {
        	throw new IllegalArgumentException("Invalid deposit amount");
           
        }
         balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
        	throw new IllegalArgumentException("Invalid withdrawal amount");
           
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
    
    public void withdraw(double balance, double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance"); 
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive"); 
        }
    }
}