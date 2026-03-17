package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidServiceException;

public class GeneralConsultation extends MedicalService {
	 
    private static final double MAX_FEE       = 2000.0;
    private static final String PREFIX        = "GC";
    private static long idCounter             = 301001L;
 
    private static final double MULTI_SESSION_DISCOUNT = 0.10;
 
    public GeneralConsultation(String patientName, double consultationFee)
            throws InvalidServiceException {
        super(patientName, consultationFee, PREFIX, idCounter);
        ++idCounter;
    }
 
    @Override
    public double calculateTotalCost(int sessions) {
        if (sessions <= 0) return 0;
        double total = consultationFee * sessions;
        if (sessions > 1) {
            total = total - (total * MULTI_SESSION_DISCOUNT);
        }
        return Math.round(total * 100.0) / 100.0;
    }
 
    @Override
    public String getServiceType() {
        return "General Consultation";
    }
 
    @Override
    public boolean isServiceValid() {
        return consultationFee <= MAX_FEE;
    }
 
    @Override
    public String getValidationMessage() {
        if (isServiceValid()) {
            return "Valid — General Consultation approved";
        }
        return "Invalid — fee ₹" + consultationFee
             + " exceeds general consultation limit of ₹" + MAX_FEE;
    }
}
