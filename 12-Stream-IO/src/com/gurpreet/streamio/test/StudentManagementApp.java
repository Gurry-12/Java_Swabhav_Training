package com.gurpreet.streamio.test;

import com.gurpreet.streamio.models.Student;
import com.gurpreet.streamio.models.StudentFileManager;

import java.util.Scanner;

public class StudentManagementApp {

    private static final StudentFileManager fileManager = new StudentFileManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcomeMessage();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        System.out.println("\nThank you for using the Student Management System.");
        scanner.close();
    }

    // -------------------------------------------------------------------------
    // Helper Methods
    // -------------------------------------------------------------------------

    private static void displayWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("  Welcome to the Student Management System");
        System.out.println("=====================================");
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add a new student");
        System.out.println("2. View all students");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static void addNewStudent() {
        System.out.println("\n--- Enter Student Details ---");

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Roll Number: ");
        long rollNumber;
        try {
            rollNumber = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid roll number format.");
            return;
        }

        System.out.print("Course: ");
        String course = scanner.nextLine().trim();

        try {
            Student student = new Student(name, rollNumber, course);
            fileManager.writeInFile(student);
            System.out.println("Student added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    private static void displayAllStudents() {
        fileManager.readInFile();
    }
}