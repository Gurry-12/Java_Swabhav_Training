package com.gurpreet.arrayobject.models;

/**
 * Intern with fixed stipend
 */
public class Intern extends Employee {
    private double monthlyStipend;
    private int duration; // in months
    
    // Constructor with constructor chaining
    public Intern(String name, String department, double monthlyStipend) {
        super(name, department);
        this.monthlyStipend = monthlyStipend;
        this.duration = 1;
    }
    
    // Overloaded constructor
    public Intern(String name, String department, double monthlyStipend, int duration) {
        this(name, department, monthlyStipend);
        this.duration = duration;
    }
    
    // Method overloading for calculateSalary
    @Override
    public double calculateSalary() {
        return monthlyStipend;
    }
    
    // Overloaded method for total stipend over duration
    public double calculateSalary(boolean totalDuration) {
        if (totalDuration) {
            return monthlyStipend * duration;
        }
        return monthlyStipend;
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Intern");
        System.out.println("Monthly Stipend: $" + monthlyStipend);
        System.out.println("Duration: " + duration + " months");
        System.out.println("Total Salary: $" + calculateSalary());
    }
}
