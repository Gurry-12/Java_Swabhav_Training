package com.gurpreet.constructor.models;

public class BankAccount {
    private final String accountNumber;
    
    // Mutable but controlled
    private String accountHolderName;
    private double balance;
    
    // Static - shared by all accounts, initialized once
    private static double interestRate = 3.5;
    
    /**
     * Primary constructor - validates all inputs
     * This is where business rules are enforced
     */
    public BankAccount(String accountNumber, String accountHolderName, double balance) {
    	 this.accountNumber = accountNumber;
        // Validation: accountNumber cannot be null or empty
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            System.out.println("Account number cannot be null or empty");
            return;
        }
        
        // Validation: accountHolderName cannot be null or empty
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            System.out.println("Account holder name cannot be null or empty");
            return;
        }
        
        // Validation: balance cannot be negative
        if (balance < 0) {
            System.out.println("Balance cannot be negative. Provided: " + balance);
            return;
        }
        
        // All validations passed - initialize object
       
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        
        System.out.println("Account created successfully: " + accountNumber);
    }
    
    
    public BankAccount(String accountNumber, String accountHolderName) {
        this(accountNumber, accountHolderName, 0.0); // Default balance = 0
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber; // Safe to return - it's immutable
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public static double getInterestRate() {
        return interestRate;
    }
    
    // Controlled setter - validates before updating
    public void setAccountHolderName(String accountHolderName) {
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            System.out.println("Account holder name cannot be null or empty");
            return;
        }
        this.accountHolderName = accountHolderName;
    }
    
    
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return;
        }
        this.balance += amount;
        System.out.println("Deposited: ₹" + amount + " | New Balance: ₹" + balance);
    }
    
    /**
     * Controlled method to modify balance with validation
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Available: ₹" + balance);
            return;
        }
        this.balance -= amount;
        System.out.println("Withdrawn: ₹" + amount + " | New Balance: ₹" + balance);
    }
    
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: ₹" + balance);
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}

