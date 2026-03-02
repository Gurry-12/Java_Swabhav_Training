package com.gurpreet.inheritance.models;

/**
 * ContractEmployee - salary calculated as fixedAmount - tax
 * Demonstrates method overriding
 */
public class ContractEmployee extends Employee {
    private double fixedAmount;
    private double taxPercentage;
    private int contractDuration; // in months
    
    /**
     * Constructor - calls parent constructor using super()
     */
    public ContractEmployee(String name, String department, double fixedAmount, double taxPercentage, int contractDuration) {
        super(name, department);
        this.fixedAmount = fixedAmount;
        this.taxPercentage = taxPercentage;
        this.contractDuration = contractDuration;
    }
    
    /**
     * Constructor overloading - default tax percentage
     */
    public ContractEmployee(String name, String department, double fixedAmount, int contractDuration) {
        this(name, department, fixedAmount, 10.0, contractDuration); // Default 10% tax
    }
    
    /**
     * Override calculateSalary() - Contract specific calculation
     * This is polymorphism in action!
     */
    @Override
    public double calculateSalary() {
        double tax = fixedAmount * (taxPercentage / 100);
        return fixedAmount - tax;
    }
    
    /**
     * Calculate total contract value
     */
    public double getTotalContractValue() {
        return calculateSalary() * contractDuration;
    }
    
    /**
     * Override displayEmployee() to show additional details
     */
    @Override
    public void displayEmployee() {
        super.displayEmployee();
        System.out.println("Type: Contract Employee");
        System.out.println("Fixed Amount: ₹" + fixedAmount);
        System.out.println("Tax: " + taxPercentage + "%");
        System.out.println("Contract Duration: " + contractDuration + " months");
        System.out.println("Total Contract Value: ₹" + getTotalContractValue());
    }
    
    // Getters
    public double getFixedAmount() {
        return fixedAmount;
    }
    
    public double getTaxPercentage() {
        return taxPercentage;
    }
    
    public int getContractDuration() {
        return contractDuration;
    }
}
