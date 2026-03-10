package com.gurpreet.inheritance.models;

/**
 * Base class for all employees
 * Demonstrates inheritance and polymorphism
 */
public abstract class Employee {
    // Static counter for auto-generating employee IDs
    private static int empCounter = 1000;
    
    // Instance variables
    protected int empId;
    protected String name;
    protected String department;
    
    /**
     * Constructor
     */
    public Employee(String name, String department) {
        this.empId = ++empCounter;
        this.name = name;
        this.department = department;
    }
    
    /**
     * Abstract method - MUST be overridden by child classes
     * This enforces "same message, different behavior"
     */
    public abstract double calculateSalary();
    
    /**
     * Display employee basic information
     * Can be overridden by child classes for additional info
     */
    public void displayEmployee() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }
    
    /**
     * Generate payslip - uses polymorphism
     * Calls calculateSalary() which behaves differently for each employee type
     */
    public void generatePayslip() {
        System.out.println("========================================");
        System.out.println("           PAYSLIP");
        System.out.println("========================================");
        displayEmployee();
        System.out.println("Salary: ₹" + calculateSalary());
        System.out.println("========================================");
    }
    
    // Getters
    public int getEmpId() {
        return empId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    // Static method
    public static int getTotalEmployees() {
        return empCounter - 1000;
    }
}
