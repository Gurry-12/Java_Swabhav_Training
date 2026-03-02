package com.gurpreet.arrayobject.models;

/**
 * Car with standard toll rate
 */
public class Car extends Vehicle {
    private static final double BASE_TOLL = 50.0;
    private boolean isLuxury;
    
    // Constructor with constructor chaining
    public Car(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
        this.isLuxury = false;
    }
    
    // Overloaded constructor
    public Car(String vehicleNumber, String ownerName, boolean isLuxury) {
        this(vehicleNumber, ownerName);
        this.isLuxury = isLuxury;
    }
    
    // Method overloading for calculateToll
    @Override
    public double calculateToll() {
        double toll = BASE_TOLL;
        if (isLuxury) {
            toll += 30.0; 
        }
        return toll;
    }
    
    public double calculateToll(double discountPercent) {
        double toll = calculateToll();
        return toll - (toll * discountPercent / 100);
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Car");
        System.out.println("Luxury: " + (isLuxury ? "Yes" : "No"));
        System.out.println("Toll Amount: $" + calculateToll());
    }
}
