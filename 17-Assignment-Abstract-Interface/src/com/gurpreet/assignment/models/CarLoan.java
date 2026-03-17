package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidLoanException;

public class CarLoan extends Loan {

	private static final double MAX_CAR_LOAN_AMOUNT = 1500000D;
	private static final String PREFIX = "CL";
	private static long idCounter = 602745L;

	public CarLoan(String borrowerName, double principalAmount, double interestRate)
			throws InvalidLoanException {
		super(borrowerName, principalAmount, interestRate, PREFIX, idCounter);
		++idCounter;
	}

	@Override
	public double calculateMonthlyInstallment(int tenureYears) {
		double totalInterest = principalAmount * interestRate / 100 * tenureYears;
		double totalAmount = principalAmount + totalInterest;
		return Math.round(totalAmount / (tenureYears * 12) * 100.0) / 100.0;
	}

	@Override
	public boolean isEligibleForLoan() {
		return principalAmount <= MAX_CAR_LOAN_AMOUNT;
	}

	@Override
	public String getEligibilityMessage() {
		if (!isEligibleForLoan()) {
			return "Not eligible - exceeds car loan limit of ₹" + MAX_CAR_LOAN_AMOUNT;
		}

		return "Eligible for Car Loan";
	}
}