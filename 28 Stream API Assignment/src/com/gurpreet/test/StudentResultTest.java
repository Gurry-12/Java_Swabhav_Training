package com.gurpreet.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.model.Student;
import com.gurpreet.service.StudentResultProcessingSystem;


public class StudentResultTest {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        StudentResultProcessingSystem processor = new StudentResultProcessingSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;

        System.out.println("==================================================");
        System.out.println("     STUDENT RESULT PROCESSING SYSTEM");
        System.out.println("==================================================\n");
        System.out.println("System initialized with no students.\n");
        System.out.println("Please use Option 1 to add students.\n");

        try {
            do {
                displayMenu();
                System.out.print("Enter your choice (0-8): ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next();
                }

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                case 1:
                    processor.addNewStudent(scanner, students);
                    break;
                case 2:
                    processor.findPassedStudents(students);
                    break;
                case 3:
                    processor.getTop3Students(students);
                    break;
                case 4:
                    processor.groupStudentsBySection(students);
                    break;
                case 5:
                    processor.countStudentsSectionWise(students);
                    break;
                case 6:
                    processor.getAverageMarksSectionWise(students);
                    break;
                case 7:
                    processor.getAllNamesInUppercase(students);
                    break;
                case 8:
                    processor.checkAnyFullMarks(students);
                    break;
                case 9:
                    processor.displayAllStudents(students);
                    break;
                case 0:
                    System.out.println("\nThank you for using Student Result Processing System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 0 and 9.");
                }

                if (choice != 0) {
                    System.out.println("\n" + "=".repeat(60) + "\n");
                }

            } while (choice != 0);

        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            
        }
    }

    private static void displayMenu() {
        System.out.println("------------------- MAIN MENU -------------------");
        System.out.println("1.  Add New Student");
        System.out.println("2.  Find Passed Students (>= 40)");
        System.out.println("3.  Get Top 3 Students by Marks");
        System.out.println("4.  Group Students by Section");
        System.out.println("5.  Count Students Section-wise");
        System.out.println("6.  Get Average Marks Section-wise");
        System.out.println("7.  Get All Student Names in Uppercase");
        System.out.println("8.  Check Any Student Scored Full Marks");
        System.out.println("9.  Display All Students");
        System.out.println("0.  Exit");
        System.out.println("-------------------------------------------------");
    }
}