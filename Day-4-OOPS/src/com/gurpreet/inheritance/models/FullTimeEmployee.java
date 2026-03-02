package com.gurpreet.inheritance.models;

/**
 * FullTimeEmployee - salary calculated as basic + HRA + DA
 * Demonstrates method overriding
 */
public class FullTimeEmployee extends Employee {
    private double basicSalary;
    private double hra;  // House Rent Allowance
    private double da;   // Dearness Allowance
    
    /**
     * Constructor - calls parent constructor using super()
     */
    public FullTimeEmployee(String name, String department, double basicSalary, double hra, double da) {
        super(name, department);
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
    }
    
    /**
     * Constructor overloading - calculate HRA and DA as percentages
     */
    public FullTimeEmployee(String name, String department, double basicSalary) {
        super(name, department);
        this.basicSalary = basicSalary;
        this.hra = basicSalary * 0.20;  // 20% of basic
        this.da = basicSalary * 0.10;   // 10% of basic
    }
    
    /**
     * Override calculateSalary() - Full-time specific calculation
     * This is polymorphism in action!
     */
    @Override
    public double calculateSalary() {
        return basicSalary + hra + da;
    }
    
    /**
     * Override displayEmployee() to show additional details
     */
    @Override
    public void displayEmployee() {
        super.displayEmployee();
        System.out.println("Type: Full-Time Employee");
        System.out.println("Basic Salary: ₹" + basicSalary);
        System.out.println("HRA: ₹" + hra);
        System.out.println("DA: ₹" + da);
    }
    
    // Getters
    public double getBasicSalary() {
        return basicSalary;
    }
    
    public double getHra() {
        return hra;
    }
    
    public double getDa() {
        return da;
    }
}
