package com.gurpreet.assigment.arrays;

import java.util.Scanner;

/**
 * Problem 5: Fraud Transaction Detector
 * Demonstrates: Arrays, pattern detection, consecutive analysis
 */
public class FraudTransactionDetector {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("     FRAUD TRANSACTION DETECTOR");
        System.out.println("========================================\n");
        
        System.out.print("Enter number of days: ");
        int n = scanner.nextInt();
        
        double[] transactions = new double[n];
        
        // Input transaction data
        System.out.println("\nEnter daily transaction amounts:");
        for (int i = 0; i < n; i++) {
            System.out.print("Day " + (i + 1) + ": ₹");
            transactions[i] = scanner.nextDouble();
        }
        
        // Task 1: Flag suspicious transactions
        System.out.println("\n========================================");
        System.out.println("       TRANSACTION ANALYSIS");
        System.out.println("========================================\n");
        
        boolean[] suspicious = new boolean[n];
        int suspiciousCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (transactions[i] > 50000) {
                suspicious[i] = true;
                suspiciousCount++;
                System.out.println("Day " + (i + 1) + ": ₹" + transactions[i] + " → ⚠ SUSPICIOUS");
            } else {
                System.out.println("Day " + (i + 1) + ": ₹" + transactions[i] + " → Normal");
            }
        }
        
        // Task 2: Detect fraud pattern (2 consecutive suspicious)
        System.out.println("\n========================================");
        System.out.println("       FRAUD PATTERN DETECTION");
        System.out.println("========================================\n");
        
        boolean fraudDetected = false;
        for (int i = 0; i < n - 1; i++) {
            if (suspicious[i] && suspicious[i + 1]) {
                System.out.println("🚨 POTENTIAL FRAUD DETECTED!");
                System.out.println("  Days " + (i + 1) + " and " + (i + 2) + 
                                 " have consecutive suspicious transactions");
                System.out.println("  Day " + (i + 1) + ": ₹" + transactions[i]);
                System.out.println("  Day " + (i + 2) + ": ₹" + transactions[i + 1]);
                System.out.println();
                fraudDetected = true;
            }
        }
        
        if (!fraudDetected) {
            System.out.println("✓ No fraud pattern detected");
        }
        
        // Task 3: Print suspicious transaction indices
        System.out.println("\n========================================");
        System.out.println("     SUSPICIOUS TRANSACTION INDICES");
        System.out.println("========================================\n");
        
        if (suspiciousCount > 0) {
            System.out.print("Indices: ");
            for (int i = 0; i < n; i++) {
                if (suspicious[i]) {
                    System.out.print(i + " ");
                }
            }
            System.out.println("\n\nDetails:");
            for (int i = 0; i < n; i++) {
                if (suspicious[i]) {
                    System.out.println("  Index " + i + " (Day " + (i + 1) + "): ₹" + transactions[i]);
                }
            }
        } else {
            System.out.println("No suspicious transactions found");
        }
        
        // Task 4: Calculate average transaction value
        double totalTransactions = 0;
        for (int i = 0; i < n; i++) {
            totalTransactions += transactions[i];
        }
        double averageTransaction = totalTransactions / n;
        
        // Display summary
        System.out.println("\n========================================");
        System.out.println("            SUMMARY");
        System.out.println("========================================");
        System.out.println("Total Days: " + n);
        System.out.println("Total Transaction Value: ₹" + totalTransactions);
        System.out.println("Average Daily Transaction: ₹" + averageTransaction);
        System.out.println("Suspicious Transactions: " + suspiciousCount);
        
        if (averageTransaction > 40000) {
            System.out.println("\n⚠ HIGH VALUE ACCOUNT");
            System.out.println("  Average daily transaction exceeds ₹40,000");
        } else {
            System.out.println("\n✓ Normal Value Account");
        }
        
        if (fraudDetected) {
            System.out.println("\n🚨 FRAUD ALERT - Immediate investigation required!");
        }
        
        System.out.println("========================================");
        
        scanner.close();
    }
}
