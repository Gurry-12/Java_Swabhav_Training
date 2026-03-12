package com.gurpreet.arrayobject.models;

/**
 * Part-time employee paid hourly
 */
public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;
    
    // Constructor with constructor chaining
    public PartTimeEmployee(String name, String department, double hourlyRate) {
        super(name, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }
    
    // Overloaded constructor
    public PartTimeEmployee(String name, String department, double hourlyRate, int hoursWorked) {
        this(name, department, hourlyRate);
        this.hoursWorked = hoursWorked;
    }
    
    // Method overloading for calculateSalary
    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
    
    // Overloaded method with overtime
    public double calculateSalary(int overtimeHours, double overtimeRate) {
        return (hourlyRate * hoursWorked) + (overtimeHours * overtimeRate);
    }
    
    // Setter for hours worked
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Part-Time Employee");
        System.out.println("Hourly Rate: $" + hourlyRate);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Total Salary: $" + calculateSalary());
    }
}
