package com.gurpreet.miscellaneous;

import java.time.LocalDate;

public class Employee {

	private String name;
	private double salary;
	private LocalDate joiningDate;
	private String gender;

	// Constructor
	public Employee(String name, double salary, LocalDate joiningDate, String gender) {

		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be null or blank.");
		}
		if (!name.matches("[a-zA-Z ]+")) {
			throw new IllegalArgumentException("Name must contain only letters and spaces.");
		}

		if (salary < 0) {
			throw new IllegalArgumentException("Salary cannot be negative.");
		}

		if (joiningDate == null) {
			throw new IllegalArgumentException("Joining date cannot be null.");
		}

		if (gender == null || gender.isBlank()) {
			throw new IllegalArgumentException("Gender cannot be null or blank.");
		}
		if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")
				&& !gender.equalsIgnoreCase("Other")) {
			throw new IllegalArgumentException("Gender must be 'Male', 'Female', or 'Other'.");
		}

		this.name = name.trim();
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.gender = gender.trim();
	}

	// Getters
	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public String getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return String.format("Name: %-12s | Salary: ₹%-8.2f | Joining: %-12s | Gender: %s", name, salary, joiningDate,
				gender);
	}
}