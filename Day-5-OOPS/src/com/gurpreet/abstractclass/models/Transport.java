// Transport.java
package com.gurpreet.abstractclass.models;

public abstract class Transport {
	private long counter = 10000L;
    private String routeId;
    protected double baseFare;

    public Transport( double baseFare) {
        this.routeId = "T" + ++counter;
        this.baseFare = baseFare;
    }

    public abstract double calculateFare();

    public void printTicket() {
        double totalFare = calculateFare();
        System.out.printf("Ticket for Route %s%n", routeId);
        System.out.printf("Base Fare     : ₹%.2f%n", baseFare);
        System.out.printf("Total Fare    : ₹%.2f%n", totalFare);
    }
}