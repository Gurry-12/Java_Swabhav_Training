package com.gurpreet.oopsassignment.test;

import java.util.Scanner;

import com.gurpreet.oopsassignment.helpers.Helpers;
import com.gurpreet.oopsassignment.models.Account;
import com.gurpreet.oopsassignment.models.CurrentAccount;
import com.gurpreet.oopsassignment.models.SavingsAccount;

public class AccountTest {

    private static final int MAX_ACCOUNTS = 10;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Account[] accounts = new Account[MAX_ACCOUNTS];
        int accountCount = 0;

        System.out.println("=====================================");
        System.out.println("   Customer Account Management System");
        System.out.println("=====================================");

        boolean isRunning = true;
        while (isRunning) {

            displayMainMenu();

            System.out.print("Enter your choice (1-5): ");
            int choice = Helpers.validateInt(scanner);

            switch (choice) {
                case 1:
                    if (accountCount >= MAX_ACCOUNTS) {
                        System.out.println("\nMaximum account limit reached (" + MAX_ACCOUNTS + ").");
                        break;
                    }
                    accountCount = addNewAccount(scanner, accounts, accountCount);
                    break;

                case 2:
                    displayAllAccounts(accounts, accountCount);
                    break;

                case 3:
                    manageAccount(scanner, accounts, accountCount);
                    break;

                case 4:
                    System.out.println("\nTotal accounts created: " + accountCount);
                    break;

                case 5:
                    isRunning = false;
                    System.out.println("\nThank you for using the system. Goodbye.");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please select 1 to 5.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Create new account");
        System.out.println("2. Display all accounts summary");
        System.out.println("3. Manage existing account (deposit / withdraw)");
        System.out.println("4. Show total number of accounts");
        System.out.println("5. Exit");
    }

    private static int addNewAccount(Scanner scanner, Account[] accounts, int currentCount) {
        System.out.println("\n--- Create New Account ---");
        System.out.println("Account type:");
        System.out.println("  1) Savings Account");
        System.out.println("  2) Current Account");
        int type = Helpers.validateIntRange(scanner, 1, 2);

  

        System.out.print("Enter account holder name: ");
        String holder = Helpers.validateStringNonEmpty(scanner);

        System.out.print("Enter initial balance: ");
        double initial = Helpers.validateDoublePositive(scanner);

        Account newAccount = null;
        
        switch(type) {
        case 1: {
            System.out.print("Enter minimum balance requirement: ");
            double minBalance = Helpers.validateDoublePositive(scanner);
            newAccount = new SavingsAccount(holder, initial, minBalance);
            break;
        } 
        case 2: {
            System.out.print("Enter overdraft limit: ");
            double overdraft = Helpers.validateDoubleNonNegative(scanner);
            newAccount = new CurrentAccount(holder, initial, overdraft);
            break;
        }
        
        default:
        	System.out.println("Enter vlaid input");
        	
        }

        accounts[currentCount] = newAccount;
        System.out.println("\nAccount created successfully.");
        return currentCount + 1;
    }

    private static void displayAllAccounts(Account[] accounts, int count) {
        if (count == 0) {
            System.out.println("\nNo accounts exist in the system.");
            return;
        }

        System.out.println("\n=== All Accounts Summary ===");
        for (int i = 0; i < count; i++) {
            accounts[i].displayDetails();
            System.out.println("----------------------------------------");
        }
    }

    private static void manageAccount(Scanner scanner, Account[] accounts, int count) {
        if (count == 0) {
            System.out.println("\nNo accounts available to manage.");
            return;
        }

        System.out.println("\n--- Manage Account ---");
        System.out.print("Enter account number: ");
        String accNumber = Helpers.validateStringNonEmpty(scanner);

        Account selected = null;
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber().equals(accNumber)) {
                selected = accounts[i];
                break;
            }
        }

        if (selected == null) {
            System.out.println("Account number not found.");
            return;
        }

        System.out.println("\nAccount found:");
        selected.displayDetails();

        boolean manage = true;
        while (manage) {
            displayManageMenu();
            System.out.print("Enter choice (1-4): ");
            int subChoice = Helpers.validateInt(scanner);

            switch (subChoice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmt = Helpers.validateDoublePositive(scanner);
                    try {
                        selected.deposit(depositAmt);
                        System.out.println("Deposit successful");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmt = Helpers.validateDoublePositive(scanner);
                    try {
                        selected.withdraw(withdrawAmt);
                        System.out.println("Withdrawal successful");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Transaction failed: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\nCurrent details:");
                    selected.displayDetails();
                    break;

                case 4:
                    manage = false;
                    System.out.println("Returning to main menu.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1–4.");
            }
        }
    }

    private static void displayManageMenu() {
        System.out.println("\nManage Account Menu:");
        System.out.println("1. Deposit money");
        System.out.println("2. Withdraw money");
        System.out.println("3. View current details");
        System.out.println("4. Back to main menu");
    }
}