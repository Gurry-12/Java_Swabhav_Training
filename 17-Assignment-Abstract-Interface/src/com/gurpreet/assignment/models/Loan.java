package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidLoanException;
import com.gurpreet.assignment.interfaces.LoanEligible;

public abstract class Loan implements LoanEligible {

	private String loanId;
	protected String borrowerName;
	protected double principalAmount;
	protected double interestRate;

	protected static final String bankName;
	protected static final double minInterestRateAllowed;
	protected static int minLoanIdLength;

	static {
		System.out.println("Initializing global loan system configuration...");
		bankName = "Swabhav Bank";
		minInterestRateAllowed = 4.0;
		minLoanIdLength = 10;
		System.out.println("Tenure time : 10 years");
		System.out.println("Configuration loaded: " + bankName);

	}

	public Loan(String borrowerName, double principalAmount, double interestRate, String prefix, long idCounter)
			throws InvalidLoanException {

		if (borrowerName == null || borrowerName.trim().isEmpty()) {
			throw new InvalidLoanException("Borrower name cannot be empty.");
		}
		if (principalAmount <= 0) {
			throw new InvalidLoanException("Principal amount must be positive.");
		}
		if (interestRate < minInterestRateAllowed) {
			throw new InvalidLoanException("Interest rate cannot be less than " + minInterestRateAllowed + "%.");
		}

		this.loanId = prefix + String.format("%d", idCounter);
		this.borrowerName = borrowerName.trim();
		this.principalAmount = principalAmount;
		this.interestRate = interestRate;

		System.out.println("Loan created : " + this.getBasicInfo());
	}

	public String getBasicInfo() {
		return "LoanID: " + loanId + " | " + borrowerName + " | ₹" + String.format("%.2f", principalAmount) + " | "
				+ String.format("%.2f", interestRate) + "%";
	}

	public abstract double calculateMonthlyInstallment(int tenureYears);

	public static String getBankName() {
		return bankName;
	}

}