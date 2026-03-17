package com.gurpreet.assignment.models;

import com.gurpreet.assignment.enums.SurgeryTypes;
import com.gurpreet.assignment.exceptions.InvalidServiceException;

public class Surgery extends MedicalService {
	 
    private static final double MAX_FEE          = 500000.0;
    private static final double MIN_FEE_REQUIRED = 5000.0;
    private static final String PREFIX           = "SG";
    private static long idCounter                = 802001L;
 
    private SurgeryTypes surgeryType;
 
    
    private static final double THEATRE_COST_PER_SESSION = 15000.0;
 
    public Surgery(String patientName, double consultationFee, SurgeryTypes surgeryType)
            throws InvalidServiceException {
        super(patientName, consultationFee, PREFIX, idCounter);
 
        if (surgeryType == null) {
            throw new InvalidServiceException("Surgery type cannot be empty.");
        }
        if (consultationFee < MIN_FEE_REQUIRED) {
            throw new InvalidServiceException(
                "Surgery fee cannot be less than ₹" + MIN_FEE_REQUIRED + ".");
        }
 
        this.surgeryType = surgeryType;
        ++idCounter;
    }
 
    
    @Override
    public double calculateTotalCost(int sessions) {
        if (sessions <= 0) return 0;
        double total = consultationFee + (THEATRE_COST_PER_SESSION * sessions);
        return Math.round(total * 100.0) / 100.0;
    }
 
    @Override
    public String getServiceType() {
        return surgeryType.toString();
    }
 
    @Override
    public boolean isServiceValid() {
        return consultationFee >= MIN_FEE_REQUIRED && consultationFee <= MAX_FEE;
    }
 
    @Override
    public String getValidationMessage() {
        if (isServiceValid()) {
            return "Valid — Surgery approved for: " + surgeryType;
        }
        if (consultationFee < MIN_FEE_REQUIRED) {
            return "Invalid — surgery fee ₹" + consultationFee
                 + " is below minimum required ₹" + MIN_FEE_REQUIRED;
        }
        return "Invalid — surgery fee ₹" + consultationFee
             + " exceeds maximum allowed ₹" + MAX_FEE;
    }
}
