package com.gurpreet.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.model.Patient;
import com.gurpreet.service.HospitalPatientAnalyzer;

public class HospitalPatientTest {

    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();
        HospitalPatientAnalyzer analyzer = new HospitalPatientAnalyzer();
        Scanner scanner = new Scanner(System.in);

        int choice;

        System.out.println("==================================================");
        System.out.println("     HOSPITAL PATIENT RECORD ANALYZER");
        System.out.println("==================================================\n");
        System.out.println("System initialized with no patients.\n");
        System.out.println("Please use Option 1 to add patients.\n");

        try {
            do {
                displayMenu();
                System.out.print("Enter your choice (0-9): ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next();
                }

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                case 1:
                    analyzer.addNewPatient(scanner, patients);
                    break;
                case 2:
                    analyzer.getAllAdmittedPatients(patients);
                    break;
                case 3:
                    analyzer.groupPatientsByDisease(patients);
                    break;
                case 4:
                    analyzer.countAdmittedVsNonAdmitted(patients);
                    break;
                case 5:
                    analyzer.findPatientWithHighestBill(patients);
                    break;
                case 6:
                    analyzer.calculateAverageBillAmount(patients);
                    break;
                case 7:
                    analyzer.getSeniorPatientsNames(patients);
                    break;
                case 8:
                    analyzer.getDiseaseToPatientNamesMap(patients);
                    break;
                case 9:
                    analyzer.displayAllPatients(patients);
                    break;
                case 0:
                    System.out.println("\nThank you for using Hospital Patient Record Analyzer. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 0 and 9.");
                }

                if (choice != 0) {
                    System.out.println("\n" + "=".repeat(60) + "\n");
                }

            } while (choice != 0);

        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            
        }
    }

    private static void displayMenu() {
        System.out.println("------------------- MAIN MENU -------------------");
        System.out.println("1.  Add New Patient");
        System.out.println("2.  Get All Admitted Patients");
        System.out.println("3.  Group Patients by Disease");
        System.out.println("4.  Count Admitted vs Non-Admitted");
        System.out.println("5.  Find Patient with Highest Bill");
        System.out.println("6.  Calculate Average Bill Amount");
        System.out.println("7.  Get Names of Patients Above Age 60");
        System.out.println("8.  Disease to Patient Names Map");
        System.out.println("9.  Display All Patients");
        System.out.println("0.  Exit");
        System.out.println("-------------------------------------------------");
    }
}