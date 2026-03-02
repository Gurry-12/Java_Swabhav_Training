package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.AccountOperations;

public class LoanAccount implements AccountOperations {
 private double balance; // Negative balance represents loan amount

 public LoanAccount(double initialLoan) {
     this.balance = -initialLoan;
 }

 @Override
 public void deposit(double amount) {
     System.out.println("Deposits not allowed on Loan Account.");
 }

 @Override
 public void withdraw(double amount) {
     if (amount < 0) {
    	 System.out.println("Withdrawal amount must be positive.");
    	 return;
     }
         balance -= amount * 1.05; 
         System.out.printf("Withdrew ₹%.2f (with 5%% interest) from Loan Account. New balance: ₹%.2f%n", amount, balance);
     
 }

 @Override
 public double checkBalance() {
     return balance;
 }
}
