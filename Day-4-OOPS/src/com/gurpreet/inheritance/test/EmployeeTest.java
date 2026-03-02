package com.gurpreet.inheritance.test;

import java.util.Scanner;
import com.gurpreet.inheritance.models.Employee;
import com.gurpreet.inheritance.models.FullTimeEmployee;
import com.gurpreet.inheritance.models.PartTimeEmployee;
import com.gurpreet.inheritance.models.ContractEmployee;
import com.gurpreet.helpers.Helpers;

/**
 * Assignment 2: Employee Payroll System
 * Demonstrates Inheritance + Polymorphism
 * 
 * Key Concepts:
 * - Same message (calculateSalary()), different behavior
 * - No if-else for type checking
 * - Method overriding
 * - Employee[] array with polymorphism
 */
public class EmployeeTest {

	private static final int MAX_EMPLOYEES = 20;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Employee[] employees = new Employee[MAX_EMPLOYEES];
		int employeeCount = 0;

		System.out.println("========================================");
		System.out.println("     EMPLOYEE PAYROLL SYSTEM");
		System.out.println("   (Inheritance + Polymorphism Demo)");
		System.out.println("========================================\n");

		boolean running = true;
		while (running) {
			displayMenu();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				if (employeeCount < MAX_EMPLOYEES) {
					employeeCount = addEmployee(scanner, employees, employeeCount);
				} else {
					System.out.println("Maximum employee limit reached.");
				}
				break;

			case 2:
				displayAllEmployees(employees, employeeCount);
				break;

			case 3:
				calculateAllSalaries(employees, employeeCount);
				break;

			case 4:
				generateAllPayslips(employees, employeeCount);
				break;

			case 5:
				generateSinglePayslip(scanner, employees, employeeCount);
				break;

			case 6:
				displayPayrollSummary(employees, employeeCount);
				break;

			case 7:
				demonstratePolymorphism(employees, employeeCount);
				break;

			case 8:
				running = false;
				System.out.println("Thank you for using Payroll System.");
				break;

			default:
				System.out.println("Invalid choice. Please enter 1-8.");
			}

			System.out.println();
		}

		scanner.close();
	}

	private static void displayMenu() {
		System.out.println("-----------------------------");
		System.out.println("     Payroll Menu");
		System.out.println("1. Add Employee");
		System.out.println("2. Display All Employees");
		System.out.println("3. Calculate All Salaries");
		System.out.println("4. Generate All Payslips");
		System.out.println("5. Generate Single Payslip");
		System.out.println("6. Display Payroll Summary");
		System.out.println("7. Demonstrate Polymorphism");
		System.out.println("8. Exit");
		System.out.println("-----------------------------");
	}

	private static int addEmployee(Scanner scanner, Employee[] employees, int count) {
		System.out.println("\nSelect employee type:");
		System.out.println("1. Full-Time Employee");
		System.out.println("2. Part-Time Employee");
		System.out.println("3. Contract Employee");
		System.out.print("Enter choice: ");

		int type = Helpers.validateInt(scanner);
		scanner.nextLine();

		switch (type) {
		case 1:
			employees[count] = createFullTimeEmployee(scanner);
			count++;
			System.out.println("✓ Full-time employee added successfully.");
			break;

		case 2:
			employees[count] = createPartTimeEmployee(scanner);
			count++;
			System.out.println("✓ Part-time employee added successfully.");
			break;

		case 3:
			employees[count] = createContractEmployee(scanner);
			count++;
			System.out.println("✓ Contract employee added successfully.");
			break;

		default:
			System.out.println("Invalid employee type.");
		}

		return count;
	}

	private static FullTimeEmployee createFullTimeEmployee(Scanner scanner) {
		System.out.println("\n--- Add Full-Time Employee ---");
		
		System.out.print("Enter name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter department: ");
		String dept = Helpers.validateString(scanner);

		System.out.print("Enter basic salary: ");
		double basic = Helpers.validateDouble(scanner);

		System.out.print("Enter HRA: ");
		double hra = Helpers.validateDouble(scanner);

		System.out.print("Enter DA: ");
		double da = Helpers.validateDouble(scanner);
		scanner.nextLine();

		return new FullTimeEmployee(name, dept, basic, hra, da);
	}

	private static PartTimeEmployee createPartTimeEmployee(Scanner scanner) {
		System.out.println("\n--- Add Part-Time Employee ---");
		
		System.out.print("Enter name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter department: ");
		String dept = Helpers.validateString(scanner);

		System.out.print("Enter hours worked: ");
		int hours = Helpers.validateInt(scanner);

		System.out.print("Enter hourly rate: ");
		double rate = Helpers.validateDouble(scanner);
		scanner.nextLine();

		return new PartTimeEmployee(name, dept, hours, rate);
	}

	private static ContractEmployee createContractEmployee(Scanner scanner) {
		System.out.println("\n--- Add Contract Employee ---");
		
		System.out.print("Enter name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter department: ");
		String dept = Helpers.validateString(scanner);

		System.out.print("Enter fixed amount: ");
		double amount = Helpers.validateDouble(scanner);

		System.out.print("Enter tax percentage: ");
		double tax = Helpers.validateDouble(scanner);

		System.out.print("Enter contract duration (months): ");
		int duration = Helpers.validateInt(scanner);
		scanner.nextLine();

		return new ContractEmployee(name, dept, amount, tax, duration);
	}

	private static void displayAllEmployees(Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees in the system.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("         ALL EMPLOYEES");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			System.out.println("Employee #" + (i + 1));
			System.out.println("----------------------------------------");
			employees[i].displayEmployee();
			System.out.println("----------------------------------------\n");
		}
	}

	/**
	 * POLYMORPHISM IN ACTION!
	 * Same method call (calculateSalary()), different behavior
	 * NO if-else to check employee type
	 */
	private static void calculateAllSalaries(Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees to calculate salaries.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("         SALARY CALCULATION");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			// Polymorphism: calculateSalary() behaves differently for each type
			double salary = employees[i].calculateSalary();
			System.out.println(employees[i].getName() + " (ID: " + 
			                   employees[i].getEmpId() + ") - ₹" + salary);
		}
	}

	/**
	 * Generate payslips for all employees
	 * Uses polymorphism - no type checking needed
	 */
	private static void generateAllPayslips(Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees to generate payslips.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("      GENERATING ALL PAYSLIPS");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			employees[i].generatePayslip();
			System.out.println();
		}
	}

	private static void generateSinglePayslip(Scanner scanner, Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees in the system.");
			return;
		}

		System.out.print("Enter employee ID: ");
		int empId = Helpers.validateInt(scanner);
		scanner.nextLine();

		for (int i = 0; i < count; i++) {
			if (employees[i].getEmpId() == empId) {
				System.out.println();
				employees[i].generatePayslip();
				return;
			}
		}
		System.out.println("Employee not found.");
	}

	private static void displayPayrollSummary(Employee[] employees, int count) {
		if (count == 0) {
			System.out.println("No employees to summarize.");
			return;
		}

		double totalPayroll = 0;
		int fullTimeCount = 0;
		int partTimeCount = 0;
		int contractCount = 0;

		// Calculate totals using polymorphism
		for (int i = 0; i < count; i++) {
			totalPayroll += employees[i].calculateSalary();
			
			// Count employee types (only for statistics)
			if (employees[i] instanceof FullTimeEmployee) {
				fullTimeCount++;
			} else if (employees[i] instanceof PartTimeEmployee) {
				partTimeCount++;
			} else if (employees[i] instanceof ContractEmployee) {
				contractCount++;
			}
		}

		System.out.println("\n========================================");
		System.out.println("         PAYROLL SUMMARY");
		System.out.println("========================================");
		System.out.println("Total Employees: " + count);
		System.out.println("  - Full-Time: " + fullTimeCount);
		System.out.println("  - Part-Time: " + partTimeCount);
		System.out.println("  - Contract: " + contractCount);
		System.out.println("----------------------------------------");
		System.out.println("Total Payroll: ₹" + totalPayroll);
		System.out.println("Average Salary: ₹" + (totalPayroll / count));
		System.out.println("========================================");
	}

	/**
	 * Demonstrate polymorphism concept
	 */
	private static void demonstratePolymorphism(Employee[] employees, int count) {
		System.out.println("\n========================================");
		System.out.println("    POLYMORPHISM DEMONSTRATION");
		System.out.println("========================================\n");

		System.out.println("Creating sample employees...\n");

		// Create different employee types
		Employee emp1 = new FullTimeEmployee("Alice", "Engineering", 50000);
		Employee emp2 = new PartTimeEmployee("Bob", "Support", 80, 500);
		Employee emp3 = new ContractEmployee("Charlie", "Marketing", 60000, 6);

		// Store in Employee array (polymorphism)
		Employee[] demoEmployees = {emp1, emp2, emp3};

		System.out.println("Calling calculateSalary() on each employee:");
		System.out.println("(Same method call, different behavior)\n");

		// Loop through and call same method - different results!
		for (int i = 0; i < demoEmployees.length; i++) {
			System.out.println(demoEmployees[i].getName() + ":");
			System.out.println("  Salary = ₹" + demoEmployees[i].calculateSalary());
			System.out.println();
		}

		System.out.println("✓ No if-else needed to determine employee type!");
		System.out.println("✓ Each class knows how to calculate its own salary!");
		System.out.println("✓ This is polymorphism in action!");
		System.out.println("\n========================================");
	}

}
