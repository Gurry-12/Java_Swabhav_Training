package com.gurpreet.abstractclass.test;

import java.util.Scanner;

import com.gurpreet.abstractclass.models.Patient;
import com.gurpreet.abstractclass.models.InPatient;
import com.gurpreet.abstractclass.models.OutPatient;
import com.gurpreet.abstractclass.models.EmergencyPatient;

import com.gurpreet.helpers.Helpers;

public class HospitalDemo {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int patientCount = 0;

		System.out.println("-----------------------------------");
		System.out.println(" Welcome to Hospital Billing System ");
		System.out.println();

		System.out.print("Enter maximum number of patients you want to add: ");
		int maxPatients = Helpers.validateInt(scanner);

		Patient[] patients = new Patient[maxPatients];

		boolean running = true;

		while (running) {

			displayDashboard();

			System.out.print("Choose an option: ");
			int choice = Helpers.validateInt(scanner);

			switch (choice) {

			case 1:
				if (patientCount >= maxPatients) {
					System.out.println("Maximum number of patients reached.");
					break;
				}
				addInPatient(scanner, patients, patientCount);
				patientCount++;
				break;

			case 2:
				if (patientCount >= maxPatients) {
					System.out.println("Maximum number of patients reached.");
					break;
				}
				addOutPatient(scanner, patients, patientCount);
				patientCount++;
				break;

			case 3:
				if (patientCount >= maxPatients) {
					System.out.println("Maximum number of patients reached.");
					return;
				}
				addEmergencyPatient(scanner, patients, patientCount);
				patientCount++;
				break;

			case 4:
				generateAllBills(patients, patientCount);
				break;

			case 5:
				running = false;
				System.out.println("Thank you for using the Hospital Billing System.");
				break;

			default:
				System.out.println("Please enter a valid choice (1-5).");
			}
		}

		scanner.close();
	}

	private static void displayDashboard() {
		System.out.println("\n-----------------------------");
		System.out.println("1. Add InPatient");
		System.out.println("2. Add OutPatient");
		System.out.println("3. Add EmergencyPatient");
		System.out.println("4. Generate All Bills");
		System.out.println("5. Exit");
		System.out.println("-----------------------------");
	}

	private static void addInPatient(Scanner scanner, Patient[] patients, int index) {

		System.out.print("Enter Patient Name: ");
		String name = scanner.next();

		System.out.print("Enter Room Charges: ");
		double roomCharges = Helpers.validateDouble(scanner);

		if (roomCharges <= 0) {
			System.out.println("Charges must be greater than 0. Patient not added.");
			return;
		}

		patients[index] = new InPatient(name, roomCharges);
		System.out.println("InPatient added successfully.");
	}

	private static void addOutPatient(Scanner scanner, Patient[] patients, int index) {

		System.out.print("Enter Patient Name: ");
		String name = scanner.next();

		System.out.print("Enter Consultation Fee: ");
		double consultationFee = Helpers.validateDouble(scanner);

		if (consultationFee <= 0) {
			System.out.println("Fee must be greater than 0. Patient not added.");
			return;
		}

		patients[index] = new OutPatient(name, consultationFee);
		System.out.println("OutPatient added successfully.");
	}

	private static void addEmergencyPatient(Scanner scanner, Patient[] patients, int index) {

		System.out.print("Enter Patient Name: ");
		String name = scanner.next();

		System.out.print("Enter Emergency Surcharge: ");
		double surcharge = Helpers.validateDouble(scanner);

		if (surcharge <= 0) {
			System.out.println("Surcharge must be greater than 0. Patient not added.");
			return;
		}

		patients[index] = new EmergencyPatient(name, surcharge);
		System.out.println("EmergencyPatient added successfully.");
	}

	private static void generateAllBills(Patient[] patients, int count) {
		if (count == 0) {
			System.out.println("No patients have been added yet.");
			return;
		}

		System.out.println("\n--- Generating Bills for All Patients ---");
		for (int i = 0; i < count; i++) {
			Patient p = patients[i];
			System.out.println("\nPatient " + (i + 1) + ":");
			p.generateBill();
			System.out.println("-----------------------------");
		}
	}
}