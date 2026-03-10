package com.gurpreet.exception.test;


public class MarksValidation {
    public static void validateMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100 inclusive.");
        }
        System.out.println("Valid marks");
    }

    public static void main(String[] args) {
        validateMarks(85);   // Valid case
        // validateMarks(120); // Uncomment to see exception
    }
}