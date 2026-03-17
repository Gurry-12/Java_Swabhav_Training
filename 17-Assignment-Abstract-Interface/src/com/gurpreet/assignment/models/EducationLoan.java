package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidLoanException;

public class EducationLoan extends Loan {
	private static final double MAX_EDU_LOAN_AMOUNT = 2000000;
	private static final String PREFIX = "EL";
	private static long idCounter = 910315L;

	public EducationLoan(String borrowerName, double principalAmount, double interestRate) throws InvalidLoanException {
		super(borrowerName, principalAmount, interestRate, PREFIX, idCounter);
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
		return principalAmount <= MAX_EDU_LOAN_AMOUNT;
	}

	@Override
	public String getEligibilityMessage() {
		if (!isEligibleForLoan()) {
			return "Not eligible - exceeds education loan limit of ₹" + MAX_EDU_LOAN_AMOUNT;
		}
		return "Eligible for Education Loan";
	}

}
