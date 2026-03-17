package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidLoanException;

public class HomeLoan extends Loan {

	private static final double MAX_HOME_LOAN_AMOUNT = 50000000D;
	private static final double MIN_CREDIT_SCORE_REQUIRED = 720;
	private static final String PREFIX = "HL";
	private static long idCounter = 100345L;
	private double creditScore;

	public HomeLoan(String borrowerName, double principalAmount, double interestRate, double creditScore)
			throws InvalidLoanException {
		super(borrowerName, principalAmount, interestRate, PREFIX, idCounter);

		if (creditScore < 1 || creditScore > 900) {
			throw new InvalidLoanException("Credit Score is not valid. ");
		}
		this.creditScore = creditScore;
		++idCounter;
	}

	@Override
	public double calculateMonthlyInstallment(int tenureYears) {
		double rate = interestRate / 12 / 100;
		double months = tenureYears * 12;
		double emi = principalAmount * rate * Math.pow(1 + rate, months) / (Math.pow(1 + rate, months) - 1);
		return Math.round(emi * 100.0) / 100.0;
	}

	@Override
	public boolean isEligibleForLoan() {
	    return principalAmount <= MAX_HOME_LOAN_AMOUNT && creditScore >= MIN_CREDIT_SCORE_REQUIRED;
	}

	@Override
	public String getEligibilityMessage() {
	    // ✅ boolean method used for the decision
	    if (isEligibleForLoan()) {
	        return "Eligible for Home Loan";
	    }
	    // ✅ individual checks only to explain WHY it failed
	    if (principalAmount > MAX_HOME_LOAN_AMOUNT && creditScore < MIN_CREDIT_SCORE_REQUIRED) {
	        return "Not eligible — amount exceeds limit and credit score " + (int)creditScore
	             + " is below minimum " + (int)MIN_CREDIT_SCORE_REQUIRED;
	    }
	    if (principalAmount > MAX_HOME_LOAN_AMOUNT) {
	        return "Not eligible — loan amount exceeds maximum limit of ₹" + MAX_HOME_LOAN_AMOUNT;
	    }
	    return "Not eligible — credit score " + (int)creditScore
	         + " is below minimum required score of " + (int)MIN_CREDIT_SCORE_REQUIRED;
	}
}
