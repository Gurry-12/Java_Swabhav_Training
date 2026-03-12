package com.gurpreet.arrayobject.models;

/**
 * Base class for all vehicles
 */
public class Vehicle {
    // Static counters
    private static int totalVehiclesProcessed = 0;
    private static double totalTollCollected = 0.0;
    
    // Instance variables
    private int vehicleId;
    private String vehicleNumber;
    private String ownerName;
    
    // Constructor
    public Vehicle(String vehicleNumber, String ownerName) {
        this.vehicleId = ++totalVehiclesProcessed;
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }
    
    // Abstract method - must be implemented by subclasses
    public double calculateToll() {
    	return 0.0;
    }
    
    // Static method to add toll to total
    public static void addToTotalToll(double amount) {
        totalTollCollected += amount;
    }
    
    // Getters
    public int getVehicleId() {
        return vehicleId;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    // Static getters
    public static int getTotalVehiclesProcessed() {
        return totalVehiclesProcessed;
    }
    
    public static double getTotalTollCollected() {
        return totalTollCollected;
    }
    
    // Display vehicle details
    public void displayDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Owner Name: " + ownerName);
    }
}
