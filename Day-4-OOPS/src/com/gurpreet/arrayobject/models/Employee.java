package com.gurpreet.arrayobject.models;

/**
 * Base class for all employees
 */
public class Employee {
    // Static counter for total employees
    private static int employeeCounter = 0;
    
    // Instance variables
    private int employeeId;
    private String name;
    private String department;
    
    // Constructor
    public Employee(String name, String department) {
        this.employeeId = ++employeeCounter;
        this.name = name;
        this.department = department;
    }
    
    
    public double calculateSalary() {
		return 0.0;
    	
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
    
    // Static method to get total employees
    public static int getTotalEmployees() {
        return employeeCounter;
    }
    
    // Display employee details
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }
}
