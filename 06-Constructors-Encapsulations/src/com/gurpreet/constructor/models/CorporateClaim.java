package com.gurpreet.constructor.models;

public class CorporateClaim extends InsuranceClaim {
	private double processingFee;
	private String companyName;

	public CorporateClaim(String policyNumber, double claimAmount, String companyName, double processingFee) {

		super(policyNumber, claimAmount);

		// Validate company name
		if (companyName == null || companyName.trim().isEmpty()) {
			System.out.println("Company name cannot be null or empty");
			return;
		}

		// Validate processing fee
		if (processingFee < 0) {
			System.out.println("Processing fee cannot be negative");
			return;
		}

		this.companyName = companyName;
		this.processingFee = processingFee;

		System.out.println("Corporate claim created for: " + companyName);
		System.out.println("Processing fee: ₹" + processingFee);
	}

	/**
	 * Constructor overloading - default processing fee
	 */
	public CorporateClaim(String policyNumber, double claimAmount, String companyName) {
		this(policyNumber, claimAmount, companyName, 500.0); // Default fee
	}

	public double getProcessingFee() {
		return processingFee;
	}

	public String getCompanyName() {
		return companyName;
	}

	public double getNetApprovedAmount() {
		return getApprovedAmount() - processingFee;
	}

	@Override
	public void displayClaimInfo() {
		super.displayClaimInfo();
		System.out.println("Company Name: " + companyName);
		System.out.println("Processing Fee: ₹" + processingFee);
		System.out.println("Net Amount: ₹" + getNetApprovedAmount());
	}
}
