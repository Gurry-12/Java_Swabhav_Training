package com.gurpreet.arrayobject.models;

/**
 * Full-time employee with fixed monthly salary and benefits
 */
public class FullTimeEmployee extends Employee {
    private double monthlySalary;
    private double benefits;
    
    // Constructor with constructor chaining
    public FullTimeEmployee(String name, String department, double monthlySalary) {
        super(name, department);
        this.monthlySalary = monthlySalary;
        this.benefits = 0;
    }
    
    // Overloaded constructor
    public FullTimeEmployee(String name, String department, double monthlySalary, double benefits) {
        this(name, department, monthlySalary);
        this.benefits = benefits;
    }
    
    // Method overloading for calculateSalary
    @Override
    public double calculateSalary() {
        return monthlySalary + benefits;
    }
    
    // Overloaded method with bonus
    public double calculateSalary(double bonus) {
        return monthlySalary + benefits + bonus;
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Full-Time Employee");
        System.out.println("Monthly Salary: $" + monthlySalary);
        System.out.println("Benefits: $" + benefits);
        System.out.println("Total Salary: $" + calculateSalary());
    }
}
