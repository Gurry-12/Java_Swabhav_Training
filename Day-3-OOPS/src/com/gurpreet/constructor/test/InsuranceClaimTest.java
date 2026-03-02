package com.gurpreet.constructor.test;

import java.util.Scanner;
import com.gurpreet.constructor.models.InsuranceClaim;
import com.gurpreet.constructor.models.CorporateClaim;
import com.gurpreet.helpers.Helpers;

/**
 * Test for Question 2: Insurance Claim System - Controlled Initialization
 */
public class InsuranceClaimTest {

	private static final int MAX_CLAIMS = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		InsuranceClaim[] claims = new InsuranceClaim[MAX_CLAIMS];
		int claimCount = 0;

		System.out.println("========================================");
		System.out.println("  INSURANCE CLAIM SYSTEM - CONSTRUCTOR DEMO");
		System.out.println("========================================\n");

		boolean running = true;
		while (running) {
			displayMenu();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				if (claimCount < MAX_CLAIMS) {
					InsuranceClaim claim = fileRegularClaim(scanner);
					if (claim != null) {
						claims[claimCount++] = claim;
					}
				} else {
					System.out.println("Maximum claim limit reached.");
				}
				break;

			case 2:
				if (claimCount < MAX_CLAIMS) {
					InsuranceClaim claim = fileCorporateClaim(scanner);
					if (claim != null) {
						claims[claimCount++] = claim;
					}
				} else {
					System.out.println("Maximum claim limit reached.");
				}
				break;

			case 3:
				approveClaim(scanner, claims, claimCount);
				break;

			case 4:
				rejectClaim(scanner, claims, claimCount);
				break;

			case 5:
				settleClaim(scanner, claims, claimCount);
				break;

			case 6:
				displayAllClaims(claims, claimCount);
				break;

			case 7:
				searchClaim(scanner, claims, claimCount);
				break;

			case 8:
				demonstrateValidation();
				break;

			case 9:
				running = false;
				System.out.println("Thank you for using Insurance Claim System.");
				break;

			default:
				System.out.println("Invalid choice. Please enter 1-9.");
			}

			System.out.println();
		}

		scanner.close();
	}

	private static void displayMenu() {
		System.out.println("-----------------------------");
		System.out.println("     Insurance Claim Menu");
		System.out.println("1. File Regular Claim");
		System.out.println("2. File Corporate Claim");
		System.out.println("3. Approve Claim");
		System.out.println("4. Reject Claim");
		System.out.println("5. Settle Claim");
		System.out.println("6. Display All Claims");
		System.out.println("7. Search Claim by ID");
		System.out.println("8. Demonstrate Constructor Validation");
		System.out.println("9. Exit");
		System.out.println("-----------------------------");
	}

	private static InsuranceClaim fileRegularClaim(Scanner scanner) {
		System.out.println("\n--- File Regular Claim ---");
		
		System.out.print("Enter policy number: ");
		String policyNum = Helpers.validateString(scanner);

		System.out.print("Enter claim amount: ");
		double amount = Helpers.validateDouble(scanner);
		scanner.nextLine();

		try {
			InsuranceClaim claim = new InsuranceClaim(policyNum, amount);
			System.out.println("✓ Claim filed successfully!");
			return claim;
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
			return null;
		}
	}

	private static InsuranceClaim fileCorporateClaim(Scanner scanner) {
		System.out.println("\n--- File Corporate Claim ---");
		
		System.out.print("Enter policy number: ");
		String policyNum = Helpers.validateString(scanner);

		System.out.print("Enter claim amount: ");
		double amount = Helpers.validateDouble(scanner);

		System.out.print("Enter company name: ");
		String company = scanner.next();

		System.out.print("Enter processing fee: ");
		double fee = Helpers.validateDouble(scanner);
		scanner.nextLine();

		try {
			CorporateClaim claim = new CorporateClaim(policyNum, amount, company, fee);
			System.out.println("✓ Corporate claim filed successfully!");
			return claim;
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
			return null;
		}
	}

	private static void approveClaim(Scanner scanner, InsuranceClaim[] claims, int count) {
		if (count == 0) {
			System.out.println("No claims available.");
			return;
		}

		System.out.print("Enter claim ID to approve: ");
		int claimId = Helpers.validateInt(scanner);

		System.out.print("Enter approved amount: ");
		double approvedAmount = Helpers.validateDouble(scanner);
		scanner.nextLine();

		for (int i = 0; i < count; i++) {
			if (claims[i].getClaimId() == claimId) {
				try {
					claims[i].approveClaim(approvedAmount);
					return;
				} catch (IllegalStateException | IllegalArgumentException e) {
					System.out.println("✗ Error: " + e.getMessage());
					return;
				}
			}
		}
		System.out.println("Claim not found.");
	}

	private static void rejectClaim(Scanner scanner, InsuranceClaim[] claims, int count) {
		if (count == 0) {
			System.out.println("No claims available.");
			return;
		}

		System.out.print("Enter claim ID to reject: ");
		int claimId = Helpers.validateInt(scanner);
		scanner.nextLine();

		for (int i = 0; i < count; i++) {
			if (claims[i].getClaimId() == claimId) {
				try {
					claims[i].rejectClaim();
					return;
				} catch (IllegalStateException e) {
					System.out.println("✗ Error: " + e.getMessage());
					return;
				}
			}
		}
		System.out.println("Claim not found.");
	}

	private static void settleClaim(Scanner scanner, InsuranceClaim[] claims, int count) {
		if (count == 0) {
			System.out.println("No claims available.");
			return;
		}

		System.out.print("Enter claim ID to settle: ");
		int claimId = Helpers.validateInt(scanner);
		scanner.nextLine();

		for (int i = 0; i < count; i++) {
			if (claims[i].getClaimId() == claimId) {
				try {
					claims[i].settleClaim();
					return;
				} catch (IllegalStateException e) {
					System.out.println("✗ Error: " + e.getMessage());
					return;
				}
			}
		}
		System.out.println("Claim not found.");
	}

	private static void displayAllClaims(InsuranceClaim[] claims, int count) {
		if (count == 0) {
			System.out.println("No claims filed yet.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("         ALL CLAIMS");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			System.out.println("Claim #" + (i + 1));
			System.out.println("----------------------------------------");
			claims[i].displayClaimInfo();
			System.out.println("----------------------------------------\n");
		}
	}

	private static void searchClaim(Scanner scanner, InsuranceClaim[] claims, int count) {
		if (count == 0) {
			System.out.println("No claims available.");
			return;
		}

		System.out.print("Enter claim ID to search: ");
		int claimId = Helpers.validateInt(scanner);
		scanner.nextLine();

		for (int i = 0; i < count; i++) {
			if (claims[i].getClaimId() == claimId) {
				System.out.println("\nClaim found:");
				System.out.println("----------------------------------------");
				claims[i].displayClaimInfo();
				System.out.println("----------------------------------------");
				return;
			}
		}
		System.out.println("Claim not found.");
	}

	private static void demonstrateValidation() {
		System.out.println("\n========================================");
		System.out.println("  CONSTRUCTOR VALIDATION DEMONSTRATION");
		System.out.println("========================================\n");

		// Test 1: Negative claim amount
		System.out.println("Test 1: Trying to file claim with negative amount...");
		try {
			InsuranceClaim claim1 = new InsuranceClaim("POL001", -5000);
			System.out.println("✗ Should have failed!");
		} catch (IllegalArgumentException e) {
			System.out.println("✓ Correctly rejected: " + e.getMessage());
		}

		// Test 2: Null policy number
		System.out.println("\nTest 2: Trying to file claim with null policy number...");
		try {
			InsuranceClaim claim2 = new InsuranceClaim(null, 10000);
			System.out.println("✗ Should have failed!");
		} catch (IllegalArgumentException e) {
			System.out.println("✓ Correctly rejected: " + e.getMessage());
		}

		// Test 3: Valid claim with auto-generated ID
		System.out.println("\nTest 3: Filing valid claim (note auto-generated ID)...");
		try {
			InsuranceClaim claim3 = new InsuranceClaim("POL003", 15000);
			System.out.println("✓ Claim filed successfully!");
			claim3.displayClaimInfo();
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Unexpected error: " + e.getMessage());
		}

		// Test 4: State flow validation
		System.out.println("\nTest 4: Testing state flow (Filed → Approved → Settled)...");
		try {
			InsuranceClaim claim4 = new InsuranceClaim("POL004", 20000);
			System.out.println("Status: " + claim4.getStatus());
			
			claim4.approveClaim(18000);
			System.out.println("Status: " + claim4.getStatus());
			
			claim4.settleClaim();
			System.out.println("Status: " + claim4.getStatus());
			System.out.println("✓ State flow working correctly!");
		} catch (Exception e) {
			System.out.println("✗ Error: " + e.getMessage());
		}

		System.out.println("\n========================================");
	}

}
