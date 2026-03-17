package com.gurpreet.assignment.test;

import java.util.Scanner;

import com.gurpreet.assignment.exceptions.InvalidLoanException;
import com.gurpreet.assignment.helpers.Helpers;
import com.gurpreet.assignment.models.CarLoan;
import com.gurpreet.assignment.models.EducationLoan;
import com.gurpreet.assignment.models.HomeLoan;
import com.gurpreet.assignment.models.Loan;

public class LoanTest {

	private static final int MAX_LOANS = 10;

	public static void main(String[] args) {
		Loan.getBankName();
		try (Scanner scanner = new Scanner(System.in)) {
			Loan[] loans = new Loan[MAX_LOANS];
			int loanCount = 0;

			System.out.println("=====================================");
			System.out.println("   Digital Loan Processing System");
			System.out.println("=====================================");

			boolean isRunning = true;
			while (isRunning) {

				displayMainMenu();

				System.out.print("Enter your choice (1-4): ");
				int choice = Helpers.validateIntRange(scanner, 1, 4);

				switch (choice) {
				case 1:
					if (loanCount >= MAX_LOANS) {
						System.out.println("\nMaximum loan limit reached (" + MAX_LOANS + ").");
						break;
					}
					loanCount = addNewLoan(scanner, loans, loanCount);
					break;

				case 2:
					displayAllLoans(loans, loanCount);
					break;

				case 3:
					System.out.println("\nTotal loans created: " + loanCount);
					break;

				case 4:
					isRunning = false;
					System.out.println("\nThank you for using the system. Goodbye.");
					break;

				default:
					System.out.println("\nInvalid choice. Please select 1 to 4.");
				}
			}

		}
	}

	private static void displayMainMenu() {
		System.out.println("\nMain Menu:");
		System.out.println("1. Apply for new loan");
		System.out.println("2. Display all loan applications");
		System.out.println("3. Show total number of loans");
		System.out.println("4. Exit");
	}

	private static int addNewLoan(Scanner scanner, Loan[] loans, int currentCount) {
		System.out.println("\n--- Apply for New Loan ---");
		System.out.println("Loan type:");
		System.out.println("  1) Home Loan");
		System.out.println("  2) Car Loan");
		System.out.println("  3) Education Loan");

		int type = Helpers.validateIntRange(scanner, 1, 3);

		System.out.print("Enter borrower name: ");
		String borrowerName = Helpers.validateStringLettersOnly(scanner);

		System.out.print("Enter principal amount (₹): ");
		double principal = Helpers.validateDoublePositive(scanner);

		System.out.print("Enter interest rate (%): ");
		double interestRate = Helpers.validateDoublePositive(scanner);

		Loan newLoan = null;
		try {
			switch (type) {
			case 1:
				System.out.println("Enter credit score: ");
				double creditScore = Helpers.validateIntRange(scanner, 1, 900);
				newLoan = new HomeLoan(borrowerName, principal, interestRate, creditScore);
				break;
			case 2:
				newLoan = new CarLoan(borrowerName, principal, interestRate);
				break;
			case 3:
				newLoan = new EducationLoan(borrowerName, principal, interestRate);
				break;
			}

			loans[currentCount] = newLoan;
			System.out.println("\nLoan application created successfully.");
			return currentCount + 1;

		} catch (InvalidLoanException e) {
			System.out.println("Application rejected: " + e.getMessage());
			return currentCount;
		}
	}

	private static void displayAllLoans(Loan[] loans, int count) {
		if (count == 0) {
			System.out.println("\nNo loan applications exist in the system.");
			return;
		}

		System.out.println("\n=== All Loan Applications Summary ===");
		for (int i = 0; i < count; i++) {
			Loan loan = loans[i];
			System.out.println("Loan " + (i + 1) + ":");
			System.out.println("  " + loan.getBasicInfo());
			if (loan.isEligibleForLoan()) {
				System.out.println("  Application Accepted...");
				System.out.println(
						"  Monthly EMI (10 years): ₹" + String.format("%.2f", loan.calculateMonthlyInstallment(10)));
			}

			if (!loan.isEligibleForLoan()) {
				System.out.println("  Application Rejected...");
			}

			System.out.println("  Eligibility: " + loan.getEligibilityMessage());
			System.out.println("  Bank: " + Loan.getBankName());
			System.out.println("----------------------------------------");
		}
	}

}