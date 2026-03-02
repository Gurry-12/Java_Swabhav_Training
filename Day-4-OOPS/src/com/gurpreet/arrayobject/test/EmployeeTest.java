package com.gurpreet.arrayobject.test;

import java.util.Scanner;
import com.gurpreet.arrayobject.models.Employee;
import com.gurpreet.arrayobject.models.FullTimeEmployee;
import com.gurpreet.arrayobject.models.PartTimeEmployee;
import com.gurpreet.arrayobject.models.Intern;
import com.gurpreet.helpers.Helpers;

/**
 * Test class to demonstrate Payroll System using polymorphism
 */
public class EmployeeTest {

	private static final int MAX_EMPLOYEES = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Employee[] employees = new Employee[MAX_EMPLOYEES];
		int employeeCount = 0;

		System.out.println("       COMPANY PAYROLL SYSTEM");
		System.out.println("========================================\n");

		boolean running = true;
		while (running) {
			displayMenu();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				if (employeeCount > MAX_EMPLOYEES) {
					System.out.println("Maximum employee limit reached.");
					return;
				}
				employeeCount = addEmployee(scanner, employees, employeeCount);

				break;

			case 2:
				displayAllEmployees(employees, employeeCount);
				break;

			case 3:
				displayPayrollSummary(employees, employeeCount);
				break;

			case 4:
				searchEmployee(scanner, employees, employeeCount);
				break;

			case 5:
				displayTotalEmployees();
				break;

			case 6:
				running = false;
				System.out.println("Thank you for using Payroll System.");
				break;

			default:
				System.out.println("Invalid choice. Please enter 1-6.");
			}

			System.out.println();
		}

		scanner.close();
	}

	private static void displayMenu() {
		System.out.println("     Payroll Menu");
		System.out.println("1. Add new employee");
		System.out.println("2. Display all employees");
		System.out.println("3. Display payroll summary");
		System.out.println("4. Search employee by name");
		System.out.println("5. Display total employees");
		System.out.println("6. Exit");
		System.out.println("-----------------------------");
	}

	private static int addEmployee(Scanner scanner, Employee[] employees, int count) {
		System.out.println("\nSelect employee type:");
		System.out.println("1. Full-Time Employee");
		System.out.println("2. Part-Time Employee");
		System.out.println("3. Intern");
		System.out.print("Enter choice: ");

		int type = Helpers.validateInt(scanner);
		scanner.nextLine();

		switch (type) {
		case 1:
			employees[count] = createFullTimeEmployee(scanner);
			count++;
			System.out.println("Full-time employee added successfully.");
			break;

		case 2:
			employees[count] = createPartTimeEmployee(scanner);
			count++;
			System.out.println("Part-time employee added successfully.");
			break;

		case 3:
			employees[count] = createIntern(scanner);
			count++;
			System.out.println("Intern added successfully.");
			break;

		default:
			System.out.println("Invalid employee type.");
		}

		return count;
	}

	private static FullTimeEmployee createFullTimeEmployee(Scanner scanner) {
		System.out.print("Enter name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter department: ");
		String dept = Helpers.validateString(scanner);

		System.out.print("Enter monthly salary: ");
		double salary = Helpers.validateDouble(scanner);

		System.out.print("Enter benefits amount: ");
		double benefits = Helpers.validateDouble(scanner);
		scanner.nextLine();

		return new FullTimeEmployee(name, dept, salary, benefits);
	}

	private static PartTimeEmployee createPartTimeEmployee(Scanner scanner) {
		System.out.print("Enter name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter department: ");
		String dept = Helpers.validateString(scanner);

		System.out.print("Enter hourly rate: ");
		double rate = Helpers.validateDouble(scanner);

		System.out.print("Enter hours worked: ");
		int hours = Helpers.validateInt(scanner);
		scanner.nextLine();

		return new PartTimeEmployee(name, dept, rate, hours);
	}

	private static Intern createIntern(Scanner scanner) {
		System.out.print("Enter name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter department: ");
		String dept = Helpers.validateString(scanner);

		System.out.print("Enter monthly stipend: ");
		double stipend = Helpers.validateDouble(scanner);

		System.out.print("Enter duration (months): ");
		int duration = Helpers.validateInt(scanner);
		scanner.nextLine();

		return new Intern(name, dept, stipend, duration);
	}

	private static void displayAllEmployees(Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees in the system.");
			return;
		}

		System.out.println("         ALL EMPLOYEES");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			System.out.println("Employee #" + (i + 1));
			System.out.println("----------------------------------------");
			employees[i].displayDetails();
			System.out.println("----------------------------------------\n");
		}
	}

	private static void displayPayrollSummary(Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees to calculate payroll.");
			return;
		}

		double totalPayroll = 0;

		System.out.println("         PAYROLL SUMMARY");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			double salary = employees[i].calculateSalary();
			totalPayroll += salary;
			System.out.println(employees[i].getName() + " (ID: " + employees[i].getEmployeeId() + ") - $" + salary);
		}

		System.out.println("\n----------------------------------------");
		System.out.println("Total Employees: " + count);
		System.out.println("Total Payroll: $" + totalPayroll);
		System.out.println("Average Salary: $" + (totalPayroll / count));
		System.out.println("========================================");
	}

	private static void searchEmployee(Scanner scanner, Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees in the system.");
			return;
		}

		System.out.print("Enter employee name to search: ");
		String searchName = Helpers.validateString(scanner);

		boolean found = false;
		for (int i = 0; i < count; i++) {
			if (employees[i].getName().equalsIgnoreCase(searchName)) {
				System.out.println("\nEmployee found:");
				System.out.println("----------------------------------------");
				employees[i].displayDetails();
				System.out.println("----------------------------------------");
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Employee not found.");
		}
	}

	private static void displayTotalEmployees() {
		System.out.println("Total employees created: " + Employee.getTotalEmployees());
	}

}
