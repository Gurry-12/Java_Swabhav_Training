package com.gurpreet.models;

import com.gurpreet.exceptions.InvalidIdException;
import com.gurpreet.exceptions.InvalidSalaryException;

public class Employee {
	private int employeeId;
	private String name;
	private String department;
	private double salary;
	private int experience;
	private boolean activeStatus;

	// Parameterized Constructor
	public Employee(int employeeId, String name, String department, double salary, int experience,
			boolean activeStatus) throws InvalidIdException, InvalidSalaryException {
		if(employeeId < 0) {
				throw new InvalidIdException("Id can't be negative");
		}
		
		if(name == null || name.trim().isEmpty()) {
			throw new  IllegalArgumentException("Name Can't be Empty");
		}
		
		if(department == null || department.trim().isEmpty()) {
			throw new  IllegalArgumentException("Department Can't be Empty");
		}
		
		if(salary < 0) {
			throw new  InvalidSalaryException("Salary Can't be negative");
		}
		
		if(experience < 0) {
			throw new  IllegalArgumentException("Experience Can't be negative");
		}
		
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.experience = experience;
		this.activeStatus = activeStatus;
	}

	// Getters
	public int getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}

	public int getExperience() {
		return experience;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	@Override
	public String toString() {
		return String.format("ID: %-4d | Name: %-15s | Dept: %-10s | Salary: ₹%-8.0f | Exp: %-2d yrs | Active: %s",
				employeeId, name, department, salary, experience, activeStatus ? "Yes" : "No");
	}
}