package com.gurpreet.abstractclass.models;

public class EmergencyPatient extends Patient {
    private double surcharge;

    public EmergencyPatient(String name, double surcharge) {
        super(name);
        this.surcharge = surcharge;
    }

    @Override
    public double calculateCharges() {
        return surcharge;  
    }
}

