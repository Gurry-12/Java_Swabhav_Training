package com.gurpreet.inheritance.models;

/**
 * PartTimeEmployee - salary calculated as hoursWorked * hourlyRate
 * Demonstrates method overriding
 */
public class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;
    
    /**
     * Constructor - calls parent constructor using super()
     */
    public PartTimeEmployee(String name, String department, int hoursWorked, double hourlyRate) {
        super(name, department);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    
    /**
     * Override calculateSalary() - Part-time specific calculation
     * This is polymorphism in action!
     */
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
    
    /**
     * Override displayEmployee() to show additional details
     */
    @Override
    public void displayEmployee() {
        super.displayEmployee();
        System.out.println("Type: Part-Time Employee");
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Hourly Rate: ₹" + hourlyRate);
    }
    
    // Getters
    public int getHoursWorked() {
        return hoursWorked;
    }
    
    public double getHourlyRate() {
        return hourlyRate;
    }
    
    // Setter for hours worked
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
