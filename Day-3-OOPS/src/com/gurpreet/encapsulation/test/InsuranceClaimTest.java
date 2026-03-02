package com.gurpreet.encapsulation.test;

import java.util.Scanner;
import com.gurpreet.encapsulation.models.InsuranceClaim;

public class InsuranceClaimTest {

    static Scanner scanner = new Scanner(System.in);
    private static InsuranceClaim claim;  // current active claim

    public static void main(String[] args) {

        System.out.println("Welcome to Insurance Claim System");

        boolean running = true;
        while (running) {
            displayMenu();

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            

            switch (choice) {
                case 1:
                    createClaim();
                    break;
                case 2:
                    approveClaim();
                    break;
                case 3:
                    rejectClaim();
                    break;
                case 4:
                    settleClaim();
                    break;
                case 5:
                    viewClaim();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("-----------------------------");
        System.out.println("     Claim Management Menu   ");
        System.out.println("1. Create new claim");
        System.out.println("2. Approve claim");
        System.out.println("3. Reject claim");
        System.out.println("4. Settle claim");
        System.out.println("5. View claim status");
        System.out.println("6. Exit");
    }

    private static void createClaim() {
        System.out.print("Enter policy number: ");
        String policy = scanner.nextLine();

        System.out.print("Enter claim amount: ");
        double amount = scanner.nextDouble();

        claim = new InsuranceClaim(policy, amount);
        System.out.println("Claim created. Status: " + claim.getClaimStatus());
    }

    private static void approveClaim() {
        if (claim == null) {
            System.out.println("No claim exists. Create one first.");
            return;
        }
        System.out.print("Enter approved amount: ");
        double approved = scanner.nextDouble();

        claim.approveClaim(approved);
        System.out.println("Claim approved. Approved amount: " + claim.getApprovedAmount());
    }

    private static void rejectClaim() {
        if (claim == null) {
            System.out.println("No claim exists. Create one first.");
            return;
        }
        claim.rejectClaim();
        System.out.println("Claim rejected.");
    }

    private static void settleClaim() {
        if (claim == null) {
            System.out.println("No claim exists. Create one first.");
            return;
        }
        claim.settleClaim();
        System.out.println("Claim settled. Final status: " + claim.getClaimStatus());
    }

    private static void viewClaim() {
        if (claim == null) {
            System.out.println("No claim exists.");
            return;
        }
        System.out.println("Policy number     : " + claim.getPolicyNumber());
        System.out.println("Claim amount      : " + claim.getClaimAmount());
        System.out.println("Approved amount   : " + claim.getApprovedAmount());
        System.out.println("Current status    : " + claim.getClaimStatus());
    }

    // Safe input methods
   

    
}