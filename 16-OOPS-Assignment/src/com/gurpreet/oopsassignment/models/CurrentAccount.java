package com.gurpreet.oopsassignment.models;

public class CurrentAccount extends Account {
    private double overdraftLimit;
    private final static String prefix = "CA";

    // Constructor chaining with validation
    public CurrentAccount(String accountHolderName, double initialBalance, double overdraftLimit) {
        super(accountHolderName, initialBalance,prefix);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (balance - amount < -overdraftLimit) {
            throw new IllegalArgumentException("Exceeds overdraft limit.");
        }
        balance -= amount;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Current | Overdraft Limit: $" + overdraftLimit);
    }
}