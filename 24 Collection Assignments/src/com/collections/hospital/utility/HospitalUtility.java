package com.collections.hospital.utility;

import java.util.Scanner;

import com.collections.hospital.enums.BloodGroup;
import com.collections.hospital.enums.Department;

public class HospitalUtility {

    public static void displayPatientTypes() {
        System.out.println("Select Patient Type:");
        System.out.println("1. General Patient");
        System.out.println("2. Emergency Patient");
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

    public static BloodGroup printAndGetBloodGroup(Scanner scanner) {
        BloodGroup[] groups = BloodGroup.values();
        System.out.println("Select Blood Group:");
        for (int i = 0; i < groups.length; i++) {
            System.out.println((i + 1) + " : " + groups[i]);
        }
        int choice = Helpers.validateIntRange(scanner, 1, groups.length);
        return groups[choice - 1];
    }

    public static void displayMenu() {
        System.out.println("\n====== Hospital Patient System ======");
        System.out.println("1. Register Patient");
        System.out.println("2. View Patient by ID");
        System.out.println("3. View All Patients (sorted by name)");
        System.out.println("4. View All Patients (sorted by age)");
        System.out.println("5. Call Next Patient (Token Queue)");
        System.out.println("6. View Patients by Department");
        System.out.println("7. Discharge Patient");
        System.out.println("8. Exit");
        System.out.println("=====================================");
    }
}