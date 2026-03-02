// Metro.java
package com.gurpreet.abstractclass.models;

public class Metro extends Transport {
    private int stations;

    public Metro( double baseFare, int stations) {
        super(baseFare);
        this.stations = stations;
    }

    @Override
    public double calculateFare() {
        return baseFare + (stations * 2.0); // ₹2 per station
    }
}