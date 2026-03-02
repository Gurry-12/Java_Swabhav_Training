package com.basics.arrays;

import java.util.Scanner;

/**
 * Problem 3: Electricity Usage Pattern Detector
 * Demonstrates: Arrays, pattern detection, consecutive elements
 */
public class ElectricityUsageDetector {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("  ELECTRICITY USAGE PATTERN DETECTOR");
        System.out.println("========================================\n");
        
        int[] usage = new int[30];
        
        // Input usage data
        System.out.println("Enter daily electricity usage for 30 days:");
        for (int i = 0; i < 30; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            usage[i] = scanner.nextInt();
        }
        
        // Task 1: Classify each day
        System.out.println("\n========================================");
        System.out.println("       DAILY CLASSIFICATION");
        System.out.println("========================================\n");
        
        int highConsumptionDays = 0;
        int lowUsageDays = 0;
        
        for (int i = 0; i < 30; i++) {
            String classification = classifyUsage(usage[i]);
            System.out.println("Day " + (i + 1) + " (" + usage[i] + " units) → " + classification);
            
            if (classification.equals("High Consumption")) {
                highConsumptionDays++;
            } else if (classification.equals("Low Usage Alert")) {
                lowUsageDays++;
            }
        }
        
        // Task 2: Detect overload warning (3 consecutive days > 500)
        System.out.println("\n========================================");
        System.out.println("       OVERLOAD DETECTION");
        System.out.println("========================================\n");
        
        boolean overloadDetected = false;
        for (int i = 0; i < 28; i++) {
            if (usage[i] > 500 && usage[i + 1] > 500 && usage[i + 2] > 500) {
                System.out.println("⚠ OVERLOAD WARNING detected!");
                System.out.println("  Days " + (i + 1) + ", " + (i + 2) + ", " + (i + 3) + 
                                 " have consecutive high usage");
                System.out.println("  Usage: " + usage[i] + ", " + usage[i + 1] + ", " + usage[i + 2]);
                overloadDetected = true;
                break;
            }
        }
        
        if (!overloadDetected) {
            System.out.println("✓ No overload warning detected");
        }
        
        // Task 3: Calculate monthly average
        int totalUsage = 0;
        for (int i = 0; i < 30; i++) {
            totalUsage += usage[i];
        }
        double averageUsage = totalUsage / 30.0;
        
        // Display summary
        System.out.println("\n========================================");
        System.out.println("            SUMMARY");
        System.out.println("========================================");
        System.out.println("Total Usage: " + totalUsage + " units");
        System.out.println("Average Daily Usage: " + averageUsage + " units");
        System.out.println("High Consumption Days: " + highConsumptionDays);
        System.out.println("Low Usage Days: " + lowUsageDays);
        
        if (averageUsage > 400) {
            System.out.println("\n⚠ HEAVY MONTH - Average usage exceeds 400 units");
        } else {
            System.out.println("\n✓ Normal Month");
        }
        System.out.println("========================================");
        
        scanner.close();
    }
    
    /**
     * Classify daily usage
     */
    private static String classifyUsage(int usage) {
        if (usage > 500) {
            return "High Consumption";
        } else if (usage < 100) {
            return "Low Usage Alert";
        } else {
            return "Normal Usage";
        }
    }
}
