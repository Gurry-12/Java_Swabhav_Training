package com.gurpreet.oopsassignment.test;

import java.util.Scanner;

import com.gurpreet.oopsassignment.helpers.Helpers;
import com.gurpreet.oopsassignment.models.RegularStudent;
import com.gurpreet.oopsassignment.models.ScholarshipStudent;
import com.gurpreet.oopsassignment.models.Student;

public class StudentTest {

    private static final int MAX_STUDENTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[MAX_STUDENTS];
        int count = 0;

        System.out.println("Welcome to Student Profile & Enrollment System");

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = Helpers.validateInt(scanner);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (count >= MAX_STUDENTS) {
                        System.out.println("Maximum student limit reached (" + MAX_STUDENTS + ").");
                        break;
                    }
                    count = addStudent(scanner, students, count);
                    break;
                case 2:
                    displayAllStudents(students, count);
                    break;
                case 3:
                    System.out.println("Total students enrolled: " + count);
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1–4.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Enroll new student");
        System.out.println("2. Display all student profiles");
        System.out.println("3. Show total number of students");
        System.out.println("4. Exit");
    }

    private static int addStudent(Scanner scanner, Student[] students, int index) {
        System.out.println("\nEnrolling student #" + (index + 1) + " of " + MAX_STUDENTS);
        System.out.println("1. Regular Student");
        System.out.println("2. Scholarship Student");
        int type = Helpers.validateInt(scanner);
        scanner.nextLine();

        
        System.out.print("Enter student name: ");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.print("Enter enrolled course: ");
        String course = Helpers.validateStringNonEmpty(scanner);

        Student stu = null;
        switch (type) {
            case 1:
                System.out.print("Enter base tuition fee: ");
                double fee = Helpers.validateDoubleNonNegative(scanner);
                stu = new RegularStudent(name, course, fee);
                break;
            case 2:
                System.out.print("Enter scholarship amount: ");
                double amount = Helpers.validateDoubleNonNegative(scanner);
                stu = new ScholarshipStudent(name, course, amount);
                break;
            default:
                System.out.println("Invalid category. Operation cancelled.");
                return index;
        }

        students[index] = stu;
        System.out.println("Student enrolled successfully.");
        return index + 1;
    }

    private static void displayAllStudents(Student[] students, int count) {
        if (count == 0) {
            System.out.println("No students enrolled.");
            return;
        }
        System.out.println("\n=== Student Profiles ===");
        for (int i = 0; i < count; i++) {
            students[i].processProfile();  // polymorphic
            System.out.println("---------------------");
        }
    }
}