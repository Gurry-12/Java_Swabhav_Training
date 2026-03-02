package com.gurpreet.abstractclass.models;


public class InPatient extends Patient {
    private double roomCharges;

    public InPatient(String name, double roomCharges) {
        super(name);
        this.roomCharges = roomCharges;
    }

    @Override
    public double calculateCharges() {
        return roomCharges;  // Room charges as base
    }
}

