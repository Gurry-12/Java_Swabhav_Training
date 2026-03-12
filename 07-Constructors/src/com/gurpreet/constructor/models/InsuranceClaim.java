package com.gurpreet.constructor.models;


public class InsuranceClaim {
    private static int claimCounter = 1000;
    private final int claimId;
    private final String policyNumber;
    private final double claimAmount;
    private String status;
    private double approvedAmount;
    
    
    public InsuranceClaim(String policyNumber, double claimAmount) {
    	this.claimId = ++claimCounter;
    	  this.policyNumber = policyNumber;
          this.claimAmount = claimAmount;
          
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            System.out.println("Policy number cannot be null or empty");
            return;
        }
        
        // Validate claim amount
        if (claimAmount <= 0) {
            System.out.println("Claim amount must be positive. Provided: " + claimAmount);
            return;
        }
        
        this.status = "Filed";
        this.approvedAmount = 0.0;
        
        System.out.println("Claim filed successfully. Claim ID: " + claimId);
    }
    
    // Getters only - no setters for immutable fields
    public int getClaimId() {
        return claimId;
    }
    
    public String getPolicyNumber() {
        return policyNumber;
    }
    
    public double getClaimAmount() {
        return claimAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public double getApprovedAmount() {
        return approvedAmount;
    }
    
    /**
     * Controlled state transition methods
     * These are the ONLY ways to change status
     */
    public void approveClaim(double approvedAmount) {
        if (!status.equals("Filed")) {
            System.out.println("Can only approve claims in 'Filed' status. Current: " + status);
            return;
        }
        
        if (approvedAmount <= 0 || approvedAmount > claimAmount) {
            System.out.println("Invalid approved amount: " + approvedAmount);
            return;
        }
        
        this.approvedAmount = approvedAmount;
        this.status = "Approved";
        System.out.println("Claim " + claimId + " approved for ₹" + approvedAmount);
    }
    
    public void rejectClaim() {
        if (!status.equals("Filed")) {
            System.out.println("Can only reject claims in 'Filed' status. Current: " + status);
            return;
        }
        
        this.status = "Rejected";
        this.approvedAmount = 0.0;
        System.out.println("Claim " + claimId + " rejected");
    }
    
    public void settleClaim() {
        if (!status.equals("Approved")) {
            System.out.println("Can only settle 'Approved' claims. Current: " + status);
            return;
        }
        
        this.status = "Settled";
        System.out.println("Claim " + claimId + " settled for ₹" + approvedAmount);
    }
    
    public void displayClaimInfo() {
        System.out.println("Claim ID: " + claimId);
        System.out.println("Policy Number: " + policyNumber);
        System.out.println("Claim Amount: ₹" + claimAmount);
        System.out.println("Status: " + status);
        System.out.println("Approved Amount: ₹" + approvedAmount);
    }
}
