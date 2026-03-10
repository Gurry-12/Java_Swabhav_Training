package com.gurpreet.oopsassignment.models;

public class SavingsAccount extends Account {
    private double minimumBalance;
    private final static String prefix = "SA";

    // Constructor chaining with validation
    public SavingsAccount(String accountHolderName, double initialBalance, double minimumBalance) {
        super(accountHolderName, initialBalance, prefix);
        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative.");
        }
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (balance - amount < minimumBalance) {
            throw new IllegalArgumentException("Insufficient funds; minimum balance required.");
        }
        balance -= amount;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Savings | Minimum Balance: $" + minimumBalance);
    }
}