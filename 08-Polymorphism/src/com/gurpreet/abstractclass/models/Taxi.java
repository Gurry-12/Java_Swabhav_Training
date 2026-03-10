// Taxi.java
package com.gurpreet.abstractclass.models;

public class Taxi extends Transport {
    private double distance;
    private double time;

    public Taxi( double baseFare, double distance, double time) {
        super(baseFare);
        this.distance = distance;
        this.time = time;
    }

    @Override
    public double calculateFare() {
        return baseFare + (distance * 5.0) + (time * 1.0); // ₹5/km + ₹1/min
    }
}