package com.gurpreet.encapsulation.models;

import com.gurpreet.encapsulation.shared.StatusEnum;

public class InsuranceClaim {
	private static long counter = 10000l;
    private String claimId;
    private String policyNumber;
    private double claimAmount;
    private StatusEnum claimStatus;
    private double approvedAmount;


    public InsuranceClaim(String policyNumber, double claimAmount) {
    	
        if (claimAmount < 0) {
           System.out.println("Claim amount cannot be negative.");
           return;
        }
        this.claimId = generateClaimeId();
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimStatus = StatusEnum.FILED;
        this.approvedAmount = 0.0;
    }

    private String generateClaimeId() {
		
		return "C" +  ++counter;
	}

	// Getter methods for read-only access
    public String getClaimId() {
        return claimId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public StatusEnum getClaimStatus() {
        return claimStatus;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }

    // Method to approve the claim with validation
    public void approveClaim(double amount) {
        if (claimStatus != StatusEnum.FILED) {
            System.out.println("Claim can only be approved from FILED StatusEnum.");
            return;
        }
        if (amount < 0 || amount > claimAmount) {
           System.out.println("Approved amount must be non-negative and not exceed claim amount.");
           return;
        }
        approvedAmount = amount;
        claimStatus = StatusEnum.APPROVED;
    }

    // Method to reject the claim
    public void rejectClaim() {
        if (claimStatus != StatusEnum.FILED) {
            System.out.println("Claim can only be rejected from FILED StatusEnum.");
            return;
        }
        claimStatus = StatusEnum.REJECTED;
    }

    // Method to settle the claim with validation
    public void settleClaim() {
        if (claimStatus != StatusEnum.APPROVED && claimStatus != StatusEnum.REJECTED) {
            System.out.println("Claim can only be settled from APPROVED or REJECTED StatusEnum.");
            return;
        }
        claimStatus = StatusEnum.SETTLED;
        
    }

    // Setter for policyNumber (example of controlled modification, assuming it can be updated before settlement)
    public void setPolicyNumber(String policyNumber) {
        if (claimStatus == StatusEnum.SETTLED) {
            System.out.println("Cannot modify settled claim.");
            return;
        }
        this.policyNumber = policyNumber;
    }

}
