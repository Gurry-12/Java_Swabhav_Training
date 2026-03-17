package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidServiceException;
import com.gurpreet.assignment.interfaces.ServiceValidatable;

public abstract class MedicalService implements ServiceValidatable {
	 
    private String serviceId;
    protected String patientName;
    protected double consultationFee;
 
    protected static final String hospitalName;
    protected static final double minFeeAllowed;
    protected static final String hospitalLocation;
 
    static {
        System.out.println("=========================================");
        System.out.println("  Initializing Hospital System Config...");
        hospitalName     = "Swabhav Hospital";
        minFeeAllowed    = 100.0;
        hospitalLocation = "Mumbai, India";
        System.out.println("  Hospital   : " + hospitalName);
        System.out.println("  Location   : " + hospitalLocation);
        System.out.println("  Min Fee    : ₹" + minFeeAllowed);
        System.out.println("=========================================");
    }
 
    public MedicalService(String patientName, double consultationFee, String prefix, long idCounter)
            throws InvalidServiceException {
 
        if (patientName == null || patientName.trim().isEmpty()) {
            throw new InvalidServiceException("Patient name cannot be empty.");
        }
        if (consultationFee < minFeeAllowed) {
            throw new InvalidServiceException(
                "Consultation fee cannot be less than ₹" + minFeeAllowed + ".");
        }
 
        this.serviceId      = prefix + String.format("%d", idCounter);
        this.patientName    = patientName.trim();
        this.consultationFee = consultationFee;
 
        System.out.println("Service created : " + this.getServiceInfo());
    }
 
    public String getServiceInfo() {
        return "ServiceID: " + serviceId
             + " | " + patientName
             + " | Fee: ₹" + String.format("%.2f", consultationFee);
    }
 
    public abstract double calculateTotalCost(int sessions);
 
    public abstract String getServiceType();
 
    public static String getHospitalName() {
        return hospitalName;
    }
}
