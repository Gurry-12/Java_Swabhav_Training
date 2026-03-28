package com.collections.student.utility;

import java.util.Scanner;

import com.collections.student.enums.Department;

public class StudentUtility {

    public static void displayStudentTypes() {
        System.out.println("Select Student Type:");
        System.out.println("1. Undergraduate");
        System.out.println("2. Postgraduate");
    }

    public static Department printAndGetDepartment(Scanner scanner) {
        Department[] departments = Department.values();
        System.out.println("Select Department:");
        for (int i = 0; i < departments.length; i++) {
            System.out.println((i + 1) + " : " + departments[i]);
        }
        int choice = Helpers.validateIntRange(scanner, 1, departments.length);
        return departments[choice - 1];
    }

    public static void displayMenu() {
        System.out.println("\n====== Student Performance Tracker ======");
        System.out.println("1. Add Student");
        System.out.println("2. Add Marks for Student");
        System.out.println("3. View Student by ID");
        System.out.println("4. View All Students (sorted by name)");
        System.out.println("5. View Rankings (sorted by total marks)");
        System.out.println("6. View Students by Department");
        System.out.println("7. Remove Ineligible Students (avg < 40)");
        System.out.println("8. Exit");
        System.out.println("=========================================");
    }
}