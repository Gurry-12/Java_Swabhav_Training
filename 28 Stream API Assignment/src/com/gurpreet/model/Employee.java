package com.gurpreet.model;

import com.gurpreet.enums.Department;
import com.gurpreet.exception.InvalidIdException;
import com.gurpreet.exception.InvalidSalaryException;

public class Employee {

    private String employeeId;
    private String name;
    private Department department;
    private double salary;
    private int experience;
    private boolean activeStatus;

    // Parameterized Constructor
    public Employee(String employeeId, String name, Department department, double salary, 
                    int experience, boolean activeStatus) 
            throws InvalidIdException, InvalidSalaryException {

        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new InvalidIdException("Employee ID cannot be null or empty.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name Can't be Empty");
        }

        if (department == null) {
            throw new IllegalArgumentException("Department Can't be Empty");
        }

        if (salary < 0) {
            throw new InvalidSalaryException("Salary Can't be negative");
        }

        if (experience < 0) {
            throw new IllegalArgumentException("Experience Can't be negative");
        }

        this.employeeId = employeeId.toUpperCase().trim();
        this.name = name.trim();
        this.department = department;
        this.salary = salary;
        this.experience = experience;
        this.activeStatus = activeStatus;
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department.getFullName();           
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
        return String.format("ID: %-10s | Name: %-18s | Dept: %-20s | Salary: ₹%-8.0f | Exp: %-2d yrs | Active: %s",
                employeeId, 
                name, 
                department, 
                salary, 
                experience, 
                activeStatus ? "Yes" : "No");
    }
}