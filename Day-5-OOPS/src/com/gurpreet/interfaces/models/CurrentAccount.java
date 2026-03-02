package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.AccountOperations;

public class CurrentAccount implements AccountOperations {
    private double balance;
    private static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount < 0) {
        	System.out.println("Deposit amount must be positive.");
        	return;
        }
        
            balance += amount;
            System.out.printf("Deposited ₹%.2f to Current Account. New balance: ₹%.2f%n", amount, balance);
         
    }

    @Override
    public void withdraw(double amount) {
        if (amount < 0 || (balance - amount) < -OVERDRAFT_LIMIT) {
        	System.out.println("Invalid withdrawal: exceeds overdraft limit.");
        	return;
        }
            balance -= amount;
            System.out.printf("Withdrew ₹%.2f from Current Account. New balance: ₹%.2f%n", amount, balance);
        
    }

    @Override
    public double checkBalance() {
        return balance;
    }
}