package com.gurpreet.assignment.models;

import com.gurpreet.assignment.enums.DiagnosticTests;
import com.gurpreet.assignment.exceptions.InvalidServiceException;

public class DiagnosticTest extends MedicalService {
	 
    private static final double MAX_FEE  = 50000.0;
    private static final String PREFIX   = "DT";
    private static long idCounter        = 550001L;
 
    private DiagnosticTests testName;
 
    public DiagnosticTest(String patientName, double consultationFee, DiagnosticTests testName)
            throws InvalidServiceException {
        super(patientName, consultationFee, PREFIX, idCounter);
 
        if (testName == null ) {
            throw new InvalidServiceException("Test name cannot be empty.");
        }
 
        this.testName = testName;
        ++idCounter;
    }

    @Override
    public double calculateTotalCost(int sessions) {
        if (sessions <= 0) return 0;
        double discount = Math.min((sessions - 1) * 0.05, 0.30); 
        double total = consultationFee * sessions * (1 - discount);
        return Math.round(total * 100.0) / 100.0;
    }
 
    @Override
    public String getServiceType() {
        return testName.toString();
    }
 
    @Override
    public boolean isServiceValid() {
        return consultationFee <= MAX_FEE;
    }
 
    @Override
    public String getValidationMessage() {
        if (isServiceValid()) {
            return "Valid — Diagnostic Test approved: " + testName;
        }
        return "Invalid — test fee ₹" + consultationFee
             + " exceeds diagnostic test limit of ₹" + MAX_FEE;
    }
}