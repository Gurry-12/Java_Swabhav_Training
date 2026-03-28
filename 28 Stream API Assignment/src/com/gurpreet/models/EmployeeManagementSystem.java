package com.gurpreet.models;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.gurpreet.exceptions.InvalidIdException;
import com.gurpreet.exceptions.InvalidSalaryException;
import com.gurpreet.utility.Helpers;

public class EmployeeManagementSystem {

	public void getAllActiveEmployees(List<Employee> employees) {
		System.out.println("\n--- Active Employees ---");
		List<Employee> activeList = employees.stream().filter(Employee::isActiveStatus).collect(Collectors.toList());

		if (activeList.isEmpty()) {
			System.out.println("No active employees found.");
			return;
		}
		activeList.forEach(System.out::println);
	}

	public void getEmployeesAboveSalary(List<Employee> employees, double threshold) {
		System.out.println("\n--- Employees with Salary > ₹" + threshold + " ---");
		List<Employee> highSalaryList = employees.stream().filter(e -> e.getSalary() > threshold)
				.collect(Collectors.toList());

		if (highSalaryList.isEmpty()) {
			System.out.println("No employees found with salary above ₹" + threshold);
			return;
		}
		highSalaryList.forEach(System.out::println);

	}

	public void countEmployeesDepartmentWise(List<Employee> employees) {
		System.out.println("\n--- Department-wise Employee Count ---");
		Map<String, Long> deptCount = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

		deptCount.forEach((dept, count) -> System.out.println(dept + " : " + count + " employee(s)"));
	}

	public void findHighestPaidEmployee(List<Employee> employees) {
		System.out.println("\n--- Highest Paid Employee ---");
		if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        Employee highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        
		if(highestPaid == null) {
			System.out.println("No employees found.");
			return;
		}
		System.out.println(highestPaid);
	}

	public void getNamesSortedBySalaryDescending(List<Employee> employees) {
		System.out.println("\n--- Employee Names Sorted by Salary (Descending) ---");
		List<String> sortedNames = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
				.map(Employee::getName).collect(Collectors.toList());

		for (int i = 0; i < sortedNames.size(); i++) {
			System.out.println((i + 1) + ". " + sortedNames.get(i));
		}
	}

	public void groupEmployeesByDepartment(List<Employee> employees) {
		System.out.println("\n--- Employees Grouped by Department ---");
		Map<String, List<Employee>> grouped = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		grouped.forEach((dept, empList) -> {
			System.out.println("\nDepartment: " + dept);
			empList.forEach(System.out::println);
		});
	}

	public void getAverageSalaryDepartmentWise(List<Employee> employees) {
		System.out.println("\n--- Average Salary Department-wise ---");
		Map<String, Double> avgSalary = employees.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

		avgSalary.forEach((dept, avg) -> System.out.printf("%-12s : ₹%.2f%n", dept, avg));
	}

	public void displayAllEmployees(List<Employee> employees) {
		System.out.println("\n--- All Employees ---");
		if (employees.isEmpty()) {
			System.out.println("No employees in the system.");
		} else {
			employees.forEach(System.out::println);
		}
	}

	// New Feature: Add Employee with validation
	public void addNewEmployee(Scanner scanner, List<Employee> employees) {
		System.out.println("\n=== Add New Employee ===");

		try {
			int employeeId = getValidEmployeeId(scanner, employees);
			String name = getValidName(scanner);
			String department = getValidDepartment(scanner);
			double salary = getValidSalary(scanner);
			int experience = getValidExperience(scanner);
			boolean activeStatus = getValidActiveStatus(scanner);

			Employee newEmployee = new Employee(employeeId, name, department, salary, experience, activeStatus);
			employees.add(newEmployee);

			System.out.println("\nEmployee added successfully!");
			System.out.println(newEmployee);

		} catch (InvalidIdException | InvalidSalaryException e) {
			System.out.println("Error: " + ((Throwable) e).getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
	}

	// Helper methods for validated input
	private int getValidEmployeeId(Scanner scanner, List<Employee> employees) {
		while (true) {
			System.out.print("Enter Employee ID (positive integer): ");
			if (scanner.hasNextInt()) {
				int id = scanner.nextInt();
				scanner.nextLine();
				if (id <= 0) {
					System.out.println("ID must be positive.");
					continue;
				}
				// Check for duplicate ID
				if (employees.stream().anyMatch(e -> e.getEmployeeId() == id)) {
					System.out.println("Employee with this ID already exists.");
					continue;
				}
				return id;
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
			}
		}
	}

	private String getValidName(Scanner scanner) {
		while (true) {
			System.out.print("Enter Name: ");
			String name = Helpers.validateStringLettersOnly(scanner);
			if (!name.isEmpty()) {
				return name;
			}
			System.out.println("Name cannot be empty.");
		}
	}

	private String getValidDepartment(Scanner scanner) {
		while (true) {
			System.out.print("Enter Department: ");
			String dept = Helpers.validateStringLettersOnly(scanner);
			if (!dept.isEmpty()) {
				return dept;
			}
			System.out.println("Department cannot be empty.");
		}
	}

	private double getValidSalary(Scanner scanner) {
		while (true) {
			System.out.print("Enter Salary (₹): ");
			if (scanner.hasNextDouble()) {
				double sal = Helpers.validateDoublePositive(scanner);
				if (sal >= 0) {
					return sal;
				}
				System.out.println("Salary cannot be negative.");
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
			}
		}
	}

	private int getValidExperience(Scanner scanner) {
		while (true) {
			System.out.print("Enter Experience (in years): ");
			if (scanner.hasNextInt()) {
				int exp = Helpers.validateIntPositive(scanner);
				if (exp >= 0) {
					return exp;
				}
				System.out.println("Experience cannot be negative.");
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
			}
		}
	}

	private boolean getValidActiveStatus(Scanner scanner) {
		while (true) {
			System.out.print("Is employee active? (yes/no): ");
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.equals("yes") || input.equals("y")) {
				return true;
			} else if (input.equals("no") || input.equals("n")) {
				return false;
			}
			System.out.println("Please enter 'yes' or 'no'.");
		}
	}
}