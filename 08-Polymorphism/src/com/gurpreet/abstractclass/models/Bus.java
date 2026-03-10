// Bus.java
package com.gurpreet.abstractclass.models;

public class Bus extends Transport {
    private double distance;

    public Bus(double baseFare, double distance) {
        super(baseFare);
        this.distance = distance;
    }

    @Override
    public double calculateFare() {
        return baseFare + (distance * 5.0); // ₹5 per km
    }
}