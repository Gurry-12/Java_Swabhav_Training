package com.basics.arrays;

import java.util.Scanner;

/**
 * Problem 4: Student Performance Heatmap
 * Demonstrates: 2D arrays, nested loops, average calculation
 */
public class StudentPerformanceHeatmap {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("    STUDENT PERFORMANCE HEATMAP");
        System.out.println("========================================\n");
        
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        
        int[][] marks = new int[n][5];
        String[] subjects = {"Math", "Science", "English", "History", "Computer"};
        
        // Input marks
        System.out.println("\nEnter marks for 5 subjects (out of 100):");
        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            for (int j = 0; j < 5; j++) {
                System.out.print("  " + subjects[j] + ": ");
                marks[i][j] = scanner.nextInt();
            }
        }
        
        // Task 1: Print result for each student
        System.out.println("\n========================================");
        System.out.println("       STUDENT RESULTS");
        System.out.println("========================================\n");
        
        int distinctionCount = 0;
        int firstClassCount = 0;
        int secondClassCount = 0;
        int failCount = 0;
        
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + ":");
            
            // Display marks
            for (int j = 0; j < 5; j++) {
                System.out.println("  " + subjects[j] + ": " + marks[i][j]);
            }
            
            // Calculate average
            double total = 0;
            boolean hasFailed = false;
            for (int j = 0; j < 5; j++) {
                total += marks[i][j];
                if (marks[i][j] < 35) {
                    hasFailed = true;
                }
            }
            double average = total / 5;
            
            // Determine result
            String result;
            if (hasFailed) {
                result = "Fail";
                failCount++;
            } else if (average >= 85) {
                result = "Distinction";
                distinctionCount++;
            } else if (average >= 60) {
                result = "First Class";
                firstClassCount++;
            } else if (average >= 50) {
                result = "Second Class";
                secondClassCount++;
            } else {
                result = "Pass";
            }
            
            System.out.println("  Average: " + average);
            System.out.println("  Result: " + result);
            System.out.println();
        }
        
        // Task 3: Find subject with highest overall average
        System.out.println("========================================");
        System.out.println("       SUBJECT ANALYSIS");
        System.out.println("========================================\n");
        
        double highestSubjectAvg = 0;
        String highestSubject = "";
        
        for (int j = 0; j < 5; j++) {
            double subjectTotal = 0;
            for (int i = 0; i < n; i++) {
                subjectTotal += marks[i][j];
            }
            double subjectAvg = subjectTotal / n;
            System.out.println(subjects[j] + " - Average: " + subjectAvg);
            
            if (subjectAvg > highestSubjectAvg) {
                highestSubjectAvg = subjectAvg;
                highestSubject = subjects[j];
            }
        }
        
        // Display summary
        System.out.println("\n========================================");
        System.out.println("            SUMMARY");
        System.out.println("========================================");
        System.out.println("Total Students: " + n);
        System.out.println("Distinctions: " + distinctionCount);
        System.out.println("First Class: " + firstClassCount);
        System.out.println("Second Class: " + secondClassCount);
        System.out.println("Fail: " + failCount);
        System.out.println("\nHighest Performing Subject:");
        System.out.println("  " + highestSubject + " (Average: " + highestSubjectAvg + ")");
        System.out.println("========================================");
        
        scanner.close();
    }
}
