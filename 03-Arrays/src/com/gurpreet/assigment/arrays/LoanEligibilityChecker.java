package com.gurpreet.assigment.arrays;

import java.util.Scanner;

/**
 * Problem 2: Smart Loan Eligibility Checker
 * Demonstrates: Arrays, multiple conditions, decision making
 */
public class LoanEligibilityChecker {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("    SMART LOAN ELIGIBILITY CHECKER");
        System.out.println("========================================\n");
        
        System.out.print("Enter number of applicants: ");
        int n = scanner.nextInt();
        
        int[] creditScores = new int[n];
        double[] monthlyIncomes = new double[n];
        int[] existingLoans = new int[n];
        
        // Input applicant data
        System.out.println("\nEnter applicant details:");
        for (int i = 0; i < n; i++) {
            System.out.println("\nApplicant " + (i + 1) + ":");
            System.out.print("  Credit Score: ");
            creditScores[i] = scanner.nextInt();
            System.out.print("  Monthly Income: ");
            monthlyIncomes[i] = scanner.nextDouble();
            System.out.print("  Existing Loans: ");
            existingLoans[i] = scanner.nextInt();
        }
        
        // Process eligibility
        System.out.println("\n========================================");
        System.out.println("       ELIGIBILITY RESULTS");
        System.out.println("========================================\n");
        
        int approvals = 0;
        int rejections = 0;
        int bestApplicantIndex = -1;
        int bestScore = 0;
        
        for (int i = 0; i < n; i++) {
            String result = checkEligibility(creditScores[i], monthlyIncomes[i], existingLoans[i]);
            
            System.out.println("Applicant " + (i + 1) + ":");
            System.out.println("  Credit Score: " + creditScores[i]);
            System.out.println("  Monthly Income: ₹" + monthlyIncomes[i]);
            System.out.println("  Existing Loans: " + existingLoans[i]);
            System.out.println("  Result: " + result);
            System.out.println();
            
            // Count approvals and rejections
            if (result.equals("Rejected")) {
                rejections++;
            } else {
                approvals++;
                
                // Find best approval profile
                if (creditScores[i] > bestScore) {
                    bestScore = creditScores[i];
                    bestApplicantIndex = i;
                }
            }
        }
        
        // Display summary
        System.out.println("========================================");
        System.out.println("            SUMMARY");
        System.out.println("========================================");
        System.out.println("Total Applicants: " + n);
        System.out.println("Approvals: " + approvals);
        System.out.println("Rejections: " + rejections);
        
        if (bestApplicantIndex != -1) {
            System.out.println("\nBest Approval Profile:");
            System.out.println("  Applicant: " + (bestApplicantIndex + 1));
            System.out.println("  Credit Score: " + creditScores[bestApplicantIndex]);
            System.out.println("  Monthly Income: ₹" + monthlyIncomes[bestApplicantIndex]);
            System.out.println("  Existing Loans: " + existingLoans[bestApplicantIndex]);
        }
        System.out.println("========================================");
        
        scanner.close();
    }
    
    /**
     * Check loan eligibility based on criteria
     */
    private static String checkEligibility(int creditScore, double income, int existingLoans) {
        // Check rejection criteria first
        if (creditScore < 600) {
            return "Rejected (Low Credit Score)";
        }
        if (income < 25000) {
            return "Rejected (Low Income)";
        }
        if (existingLoans >= 3) {
            return "Rejected (Too Many Existing Loans)";
        }
        
        // Check approval type
        if (creditScore >= 800 && income > 100000) {
            return "Instant Approval";
        } else {
            return "Standard Review";
        }
    }
}
