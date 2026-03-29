package com.gurpreet.miscellaneous;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeManager {

	private static final List<Employee> employees = new ArrayList<>();
	private static final Scanner scanner = new Scanner(System.in);
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static void main(String[] args) {

		System.out.println("--------------------------------");
		System.out.println("        Employee Manager        ");
		System.out.println("--------------------------------");

		boolean running = true;

		while (running) {
			printMenu();
			int choice = readInt("Enter choice: ");

			switch (choice) {
			case 1 -> addEmployee();
			case 2 -> findHighestSalary();
			case 3 -> findSecondHighestSalary();
			case 4 -> findMostSenior();
			case 5 -> countByGender();
			case 6 -> {
				System.out.println("\nGoodbye!");
				running = false;
			}
			default -> System.out.println("[!] Invalid choice. Please enter 1–6.\n");
			}
		}

		scanner.close();
	}

	// ─── Menu ────────────────────────────────────────────────────────────────────

	private static void printMenu() {
		System.out.println(" -------------------------------------- ");
		System.out.println("|  1. Add Employee                     |");
		System.out.println("|  2. Highest Salary                   |");
		System.out.println("|  3. Second Highest Salary            |");
		System.out.println("|  4. Most Senior Employee             |");
		System.out.println("|  5. Count by Gender                  |");
		System.out.println("|  6. Exit                             |");
		System.out.println(" -------------------------------------- ");
	}

	// ─── Operations ──────────────────────────────────────────────────────────────

	private static void addEmployee() {
		System.out.println("\n--- Add New Employee ---");

		String name = readString("Enter name          : ");
		double salary = readDouble("Enter salary        : ");
		LocalDate joining = readDate("Enter joining date  : (dd-MM-yyyy) ");
		String gender = readString("Enter gender        : (Male/Female/Other) ");

		try {
			Employee emp = new Employee(name, salary, joining, gender);
			employees.add(emp);
			System.out.println("[✓] Employee added successfully!\n");
		} catch (IllegalArgumentException e) {
			System.out.println("[!] Failed to add employee: " + e.getMessage() + "\n");
		}
	}

	private static void findHighestSalary() {
		System.out.println("\n--- Highest Salary ---");

		if (isEmpty())
			return;

		Employee top = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();

		printDivider();
		System.out.println("  " + top);
		printDivider();
		System.out.println();
	}

	private static void findSecondHighestSalary() {
		System.out.println("\n--- Second Highest Salary ---");

		if (isEmpty())
			return;

		double highestSalary = employees.stream().mapToDouble(Employee::getSalary).max().getAsDouble();

		employees.stream().filter(e -> e.getSalary() < highestSalary)
				.max(Comparator.comparingDouble(Employee::getSalary)).ifPresentOrElse(e -> {
					printDivider();
					System.out.println("  " + e);
					printDivider();
					System.out.println();
				}, () -> System.out.println("[!] Not enough distinct salaries to find second highest.\n"));
	}

	private static void findMostSenior() {
		System.out.println("\n--- Most Senior Employee ---");

		if (isEmpty())
			return;

		Employee senior = employees.stream().min(Comparator.comparing(Employee::getJoiningDate)).get();

		printDivider();
		System.out.println("  " + senior);
		printDivider();
		System.out.println();
	}

	private static void countByGender() {
		System.out.println("\n--- Employee Count by Gender ---");

		if (isEmpty())
			return;

		Map<String, Long> genderCount = employees.stream()
				.collect(Collectors.groupingBy(
						e -> e.getGender().substring(0, 1).toUpperCase() + e.getGender().substring(1).toLowerCase(),
						Collectors.counting()));

		printDivider();
		genderCount.forEach((gender, count) -> System.out.printf("  %-10s : %d employee(s)%n", gender, count));
		printDivider();
		System.out.printf("  %-10s : %d employee(s)%n%n", "Total", employees.size());
	}

	// ─── Helpers ─────────────────────────────────────────────────────────────────

	private static boolean isEmpty() {
		if (employees.isEmpty()) {
			System.out.println("[!] No employees found. Please add employees first.\n");
			return true;
		}
		return false;
	}

	private static void printDivider() {
		System.out.println("-".repeat(70));
	}

	private static String readString(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine().trim();
	}

	private static int readInt(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("[!] Please enter a valid whole number.");
			}
		}
	}

	private static double readDouble(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			try {
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				System.out.println("[!] Please enter a valid number (e.g. 45000.00).");
			}
		}
	}

	private static LocalDate readDate(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			try {
				return LocalDate.parse(input, DATE_FORMAT);
			} catch (DateTimeParseException e) {
				System.out.println("[!] Invalid date. Use dd-MM-yyyy (e.g. 15-08-2023).");
			}
		}
	}
}
