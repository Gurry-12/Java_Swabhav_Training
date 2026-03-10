package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.AccountOperations;

public class SavingsAccount implements AccountOperations {
    private double balance;
    private static final double WITHDRAWAL_LIMIT = 10000.0;

    public SavingsAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount < 0) {
        	 System.out.println("Deposit amount must be positive.");
        	 return;
        }
            balance += amount;
            System.out.printf("Deposited ₹%.2f to Savings Account. New balance: ₹%.2f%n", amount, balance);
        
    }

    @Override
    public void withdraw(double amount) {
        if (amount < 0 || amount <= WITHDRAWAL_LIMIT  || amount <= balance) {
        	 System.out.println("Invalid withdrawal: exceeds limit or balance.");
        	 return;
        }
            balance -= amount;
            System.out.printf("Withdrew ₹%.2f from Savings Account. New balance: ₹%.2f%n", amount, balance);
       
    }

    @Override
    public double checkBalance() {
        return balance;
    }
}