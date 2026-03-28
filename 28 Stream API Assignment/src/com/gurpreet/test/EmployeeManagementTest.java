package com.gurpreet.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.exceptions.InvalidIdException;
import com.gurpreet.exceptions.InvalidSalaryException;
import com.gurpreet.models.Employee;
import com.gurpreet.models.EmployeeManagementSystem;

public class EmployeeManagementTest {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		EmployeeManagementSystem manager = new EmployeeManagementSystem();
		Scanner scanner = new Scanner(System.in);

		int choice;

		System.out.println("==================================================");
		System.out.println("     EMPLOYEE MANAGEMENT ANALYTICS SYSTEM");
		System.out.println("==================================================\n");
		System.out.println("System initialized with no employees.\n");
		System.out.println("Please use Option 9 to add employees.\n");

		try {
			do {
				displayMenu();
				System.out.print("Enter your choice (0-9): ");

				// Input validation for choice
				while (!scanner.hasNextInt()) {
					System.out.println("Invalid input! Please enter a valid number between 0 and 9.");
					scanner.next(); // discard invalid token
				}

				choice = scanner.nextInt();
				scanner.nextLine(); // consume newline

				switch (choice) {
				case 1:
					manager.addNewEmployee(scanner, employees);
					break;
				case 2:
					manager.getAllActiveEmployees(employees);
					break;
				case 3:
					System.out.print("Enter salary threshold: ₹");
					while (!scanner.hasNextDouble()) {
						System.out.println("Invalid input! Please enter a valid number.");
						scanner.next();
					}
					double threshold = scanner.nextDouble();
					scanner.nextLine();
					manager.getEmployeesAboveSalary(employees, threshold);
					break;
				case 4:
					manager.countEmployeesDepartmentWise(employees);
					break;
				case 5:
					manager.findHighestPaidEmployee(employees);
					break;
				case 6:
					manager.getNamesSortedBySalaryDescending(employees);
					break;
				case 7:
					manager.groupEmployeesByDepartment(employees);
					break;
				case 8:
					manager.getAverageSalaryDepartmentWise(employees);
					break;
				case 9:
					manager.displayAllEmployees(employees);
					break;

				case 0:
					System.out.println("\nThank you for using Employee Management Analytics System. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice! Please enter a number between 0 and 9.");
				}

				if (choice != 0) {
					System.out.println("\n" + "=".repeat(60) + "\n");
				}

			} while (choice != 0);

		} catch (Exception e) {
			System.out.println("\nAn unexpected error occurred in the system: " + e.getMessage());
			e.printStackTrace();
		} finally {
			scanner.close();
			System.out.println("Scanner closed successfully.");
		}
	}

	private static void displayMenu() {
		System.out.println("------------------- MAIN MENU -------------------");
		System.out.println("1.  Add New Employee");
		System.out.println("2.  Get all Active Employees");
		System.out.println("3.  Get Employees with Salary above Threshold");
		System.out.println("4.  Count Employees Department-wise");
		System.out.println("5.  Find the Highest-Paid Employee");
		System.out.println("6.  Get Employee Names Sorted by Salary (Descending)");
		System.out.println("7.  Group Employees by Department");
		System.out.println("8.  Get Average Salary Department-wise");
		System.out.println("9.  Display All Employees");
		System.out.println("0.  Exit");
		System.out.println("-------------------------------------------------");
	}
}