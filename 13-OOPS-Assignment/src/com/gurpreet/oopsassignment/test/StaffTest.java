package com.gurpreet.oopsassignment.test;

import java.util.Scanner;

import com.gurpreet.oopsassignment.enums.Department;
import com.gurpreet.oopsassignment.enums.ShiftType;
import com.gurpreet.oopsassignment.enums.Specialization;
import com.gurpreet.oopsassignment.helpers.Helpers;
import com.gurpreet.oopsassignment.models.Doctor;
import com.gurpreet.oopsassignment.models.Nurse;
import com.gurpreet.oopsassignment.models.Staff;

public class StaffTest {

	private static final int MAX_STAFF = 8;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Staff[] staffList = new Staff[MAX_STAFF];
		int staffCount = 0;

		System.out.println("=====================================");
		System.out.println("   Hospital Staff Management System");
		System.out.println("=====================================");

		boolean running = true;
		while (running) {

			displayMainMenu();

			System.out.print("Enter your choice (1-4): ");
			int choice = Helpers.validateIntRange(scanner, 1, 4);

			switch (choice) {
			case 1:
				if (staffCount >= MAX_STAFF) {
					System.out.println("\nMaximum staff limit reached (" + MAX_STAFF + ").");
					break;
				}
				staffCount = addStaffMember(scanner, staffList, staffCount);
				break;

			case 2:
				displayAllStaff(staffList, staffCount);
				break;

			case 3:
				System.out.println("\nTotal staff records: " + staffCount);
				break;

			case 4:
				running = false;
				System.out.println("\nThank you for using the system. Goodbye.");
				break;
			}
		}

		scanner.close();
	}

	private static void displayMainMenu() {
		System.out.println("\nMain Menu:");
		System.out.println("1. Add new staff member");
		System.out.println("2. Display all staff records");
		System.out.println("3. Show total number of staff");
		System.out.println("4. Exit");
	}

	private static int addStaffMember(Scanner scanner, Staff[] staffList, int currentCount) {
        System.out.println("\n--- Register New Staff Member ---");

        
        System.out.print("Enter full name: ");
        String name = Helpers.validateStringLettersOnly(scanner);

        System.out.println("\nSelect department:");
        Department[] depts = Department.values();
        for (int i = 0; i < depts.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, depts[i]);
        }
        int deptIdx = Helpers.validateIntRange(scanner, 1, depts.length) - 1;
        Department dept = depts[deptIdx];

        System.out.println("\nSelect role:");
        System.out.println("  1) Doctor");
        System.out.println("  2) Nurse");
        int role = Helpers.validateIntRange(scanner, 1, 2);

        Staff newStaff = null;
        
        switch(role) {
        case 1: {
            System.out.println("\nSelect specialization:");
            Specialization[] specs = Specialization.values();
            for (int i = 0; i < specs.length; i++) {
                System.out.printf("  %d) %s%n", i + 1, specs[i]);
            }
            int specIdx = Helpers.validateIntRange(scanner, 1, specs.length) - 1;

            System.out.print("Enter years of practice: ");
            int years = Helpers.validateIntNonNegative(scanner);

            newStaff = new Doctor(name, dept, specs[specIdx], years);
            break;
        } 
        case 2: {
            System.out.println("\nSelect shift type:");
            ShiftType[] shifts = ShiftType.values();
            for (int i = 0; i < shifts.length; i++) {
                System.out.printf("  %d) %s%n", i + 1, shifts[i]);
            }
            int shiftIdx = Helpers.validateIntRange(scanner, 1, shifts.length) - 1;

            newStaff = new Nurse(name, dept, shifts[shiftIdx]);
            break;
        }
        
        default:
        	System.out.println("Enter valid input");
        	
        }

        staffList[currentCount] = newStaff;
        System.out.println("\nStaff member registered successfully.");
        newStaff.printRecord();
        return currentCount + 1;
    }

	private static void displayAllStaff(Staff[] staffList, int count) {
		if (count == 0) {
			System.out.println("\nNo staff records available.");
			return;
		}

		System.out.println("\n=== Hospital Staff Records ===");
		for (int i = 0; i < count; i++) {
			staffList[i].printRecord();
			System.out.println("----------------------------------------");
		}
	}
}