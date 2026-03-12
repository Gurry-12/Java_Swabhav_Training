package com.gurpreet.abstractclass.models;


public class OutPatient extends Patient {
    private double consultationFee;

    public OutPatient( String name, double consultationFee) {
        super(name);
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculateCharges() {
        return consultationFee;  // Consultation fee as base
    }
}

