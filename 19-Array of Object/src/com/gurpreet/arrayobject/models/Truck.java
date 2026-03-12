package com.gurpreet.arrayobject.models;

/**
 * Truck with weight-based toll calculation
 */
public class Truck extends Vehicle {
    private static final double BASE_TOLL = 100.0;
    private static final double RATE_PER_TON = 20.0;
    private double weight; // in tons
    private int axles;
    
    // Constructor with constructor chaining
    public Truck(String vehicleNumber, String ownerName, double weight) {
        super(vehicleNumber, ownerName);
        this.weight = weight;
        this.axles = 2;
    }
    
    // Overloaded constructor
    public Truck(String vehicleNumber, String ownerName, double weight, int axles) {
        this(vehicleNumber, ownerName, weight);
        this.axles = axles;
    }
    
    // Method overloading for calculateToll
    @Override
    public double calculateToll() {
        double toll = BASE_TOLL + (weight * RATE_PER_TON);
        // Additional charge for extra axles
        if (axles > 2) {
            toll += (axles - 2) * 15.0;
        }
        return toll;
    }
    
    // Overloaded method with time-based pricing
    public double calculateToll(boolean isPeakHour) {
        double toll = calculateToll();
        if (isPeakHour) {
            toll += 50.0; 
        }
        return toll;
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Truck");
        System.out.println("Weight: " + weight + " tons");
        System.out.println("Axles: " + axles);
        System.out.println("Toll Amount: $" + calculateToll());
    }
}
