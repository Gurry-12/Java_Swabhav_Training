package com.gurpreet.assigment.arrays;

import java.util.Scanner;

/**
 * Problem 1: Insurance Risk Portfolio Analyzer
 * Demonstrates: Arrays, conditional logic, classification
 */
public class InsuranceRiskAnalyzer {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("  INSURANCE RISK PORTFOLIO ANALYZER");
        System.out.println("========================================\n");
        
        System.out.print("Enter number of customers: ");
        int n = scanner.nextInt();
        
        int[] ages = new int[n];
        int[] riskScores = new int[n];
        
        // Input customer data
        System.out.println("\nEnter customer details:");
        for (int i = 0; i < n; i++) {
            System.out.println("\nCustomer " + (i + 1) + ":");
            System.out.print("  Age: ");
            ages[i] = scanner.nextInt();
            System.out.print("  Risk Score (0-100): ");
            riskScores[i] = scanner.nextInt();
        }
        
        // Task 1 & 2: Classify and count
        System.out.println("\n========================================");
        System.out.println("         RISK CLASSIFICATION");
        System.out.println("========================================\n");
        
        int highRiskYouth = 0;
        int seniorRisk = 0;
        int veryHighRisk = 0;
        int normalRisk = 0;
        
        for (int i = 0; i < n; i++) {
            String category = classifyCustomer(ages[i], riskScores[i]);
            System.out.println("Customer " + (i + 1) + " (Age: " + ages[i] + 
                             ", Risk: " + riskScores[i] + ") → " + category);
            
            // Count categories
            if (category.equals("High Risk Youth")) {
                highRiskYouth++;
            } else if (category.equals("Senior Risk")) {
                seniorRisk++;
            } else if (category.equals("Very High Risk")) {
                veryHighRisk++;
            } else {
                normalRisk++;
            }
        }
        
        // Task 3: Calculate average risk score
        double totalRisk = 0;
        for (int i = 0; i < n; i++) {
            totalRisk += riskScores[i];
        }
        double averageRisk = totalRisk / n;
        
        // Task 4: Find highest risk customer
        int highestRiskIndex = 0;
        int highestRiskScore = riskScores[0];
        for (int i = 1; i < n; i++) {
            if (riskScores[i] > highestRiskScore) {
                highestRiskScore = riskScores[i];
                highestRiskIndex = i;
            }
        }
        
        // Display summary
        System.out.println("\n========================================");
        System.out.println("            SUMMARY");
        System.out.println("========================================");
        System.out.println("Category Breakdown:");
        System.out.println("  High Risk Youth: " + highRiskYouth);
        System.out.println("  Senior Risk: " + seniorRisk);
        System.out.println("  Very High Risk: " + veryHighRisk);
        System.out.println("  Normal Risk: " + normalRisk);
        System.out.println("\nAverage Risk Score: " + averageRisk);
        System.out.println("\nHighest Risk Customer:");
        System.out.println("  Index: " + highestRiskIndex);
        System.out.println("  Customer: " + (highestRiskIndex + 1));
        System.out.println("  Age: " + ages[highestRiskIndex]);
        System.out.println("  Risk Score: " + highestRiskScore);
        System.out.println("========================================");
        
        scanner.close();
    }
    
    /**
     * Classify customer based on age and risk score
     */
    private static String classifyCustomer(int age, int riskScore) {
        // Check conditions in order of priority
        if (age < 25 && riskScore > 70) {
            return "High Risk Youth";
        } else if (age >= 60) {
            return "Senior Risk";
        } else if (riskScore >= 85) {
            return "Very High Risk";
        } else {
            return "Normal Risk";
        }
    }
}
