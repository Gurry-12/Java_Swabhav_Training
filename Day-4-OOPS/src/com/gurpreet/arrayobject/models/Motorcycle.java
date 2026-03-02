package com.gurpreet.arrayobject.models;

/**
 * Motorcycle with lowest toll rate
 */
public class Motorcycle extends Vehicle {
    private static final double BASE_TOLL = 20.0;
    private int engineCapacity; // in CC
    
    // Constructor with constructor chaining
    public Motorcycle(String vehicleNumber, String ownerName) {
        super(vehicleNumber, ownerName);
        this.engineCapacity = 150;
    }
    
    // Overloaded constructor
    public Motorcycle(String vehicleNumber, String ownerName, int engineCapacity) {
        this(vehicleNumber, ownerName);
        this.engineCapacity = engineCapacity;
    }
    
    // Method overloading for calculateToll
    @Override
    public double calculateToll() {
        double toll = BASE_TOLL;
        // Higher capacity bikes pay more
        if (engineCapacity > 500) {
            toll += 15.0;
        } else if (engineCapacity > 250) {
            toll += 10.0;
        }
        return toll;
    }
    
    // Overloaded method with pass type
    public double calculateToll(String passType) {
        double toll = calculateToll();
        if (passType.equalsIgnoreCase("monthly")) {
            return toll * 0.5; // 50% discount for monthly pass
        } else if (passType.equalsIgnoreCase("annual")) {
            return toll * 0.3; // 70% discount for annual pass
        }
        return toll;
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Motorcycle");
        System.out.println("Engine Capacity: " + engineCapacity + " CC");
        System.out.println("Toll Amount: $" + calculateToll());
    }
}
