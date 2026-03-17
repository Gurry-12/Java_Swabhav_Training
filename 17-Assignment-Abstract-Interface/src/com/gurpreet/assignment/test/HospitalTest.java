package com.gurpreet.assignment.test;

import java.util.Scanner;

import com.gurpreet.assignment.enums.DiagnosticTests;
import com.gurpreet.assignment.enums.SurgeryTypes;
import com.gurpreet.assignment.exceptions.InvalidServiceException;
import com.gurpreet.assignment.helpers.Helpers;
import com.gurpreet.assignment.models.DiagnosticTest;
import com.gurpreet.assignment.models.GeneralConsultation;
import com.gurpreet.assignment.models.MedicalService;
import com.gurpreet.assignment.models.Surgery;

public class HospitalTest {

	private static final int MAX_SERVICES = 10;

	public static void main(String[] args) {

		// Force static block to run at startup
		MedicalService.getHospitalName();

		try (Scanner scanner = new Scanner(System.in)) {

			MedicalService[] services = new MedicalService[MAX_SERVICES];
			int serviceCount = 0;

			System.out.println("\n=====================================");
			System.out.println("   Hospital Appointment System");
			System.out.println("=====================================");

			boolean isRunning = true;
			while (isRunning) {

				displayMainMenu();

				System.out.print("Enter your choice (1-4): ");
				int choice = Helpers.validateIntRange(scanner, 1, 4);

				switch (choice) {
				case 1:
					if (serviceCount >= MAX_SERVICES) {
						System.out.println("\nMaximum service limit reached (" + MAX_SERVICES + ").");
						break;
					}
					serviceCount = addNewService(scanner, services, serviceCount);
					break;

				case 2:
					displayAllServices(services, serviceCount);
					break;

				case 3:
					System.out.println("\nTotal services registered: " + serviceCount);
					break;

				case 4:
					isRunning = false;
					System.out.println("\nThank you for using " + MedicalService.getHospitalName() + ". Goodbye.");
					break;

				default:
					System.out.println("\nInvalid choice. Please select 1 to 4.");
				}
			}
		}
	}

	private static void displayMainMenu() {
		System.out.println("\nMain Menu:");
		System.out.println("1. Register new service");
		System.out.println("2. Display all services");
		System.out.println("3. Show total number of services");
		System.out.println("4. Exit");
	}

	private static int addNewService(Scanner scanner, MedicalService[] services, int currentCount) {
		System.out.println("\n--- Register New Service ---");
		System.out.println("Service type:");
		System.out.println("  1) General Consultation");
		System.out.println("  2) Surgery");
		System.out.println("  3) Diagnostic Test");

		int type = Helpers.validateIntRange(scanner, 1, 3);

		System.out.print("Enter patient name: ");
		String patientName = Helpers.validateStringNonEmpty(scanner);

		System.out.print("Enter consultation fee (₹): ");
		double fee = Helpers.validateDoubleNonNegative(scanner);

		MedicalService newService = null;

		try {
			switch (type) {
			case 1:
				newService = new GeneralConsultation(patientName, fee);
				break;

			case 2:
				System.out.println("Enter surgery type : ");
				SurgeryTypes[] surgeryType = SurgeryTypes.values();
				for (int i = 0; i < surgeryType.length; i++) {
					System.out.printf("  %d) %s%n", i + 1, surgeryType[i]);
				}
				int surgeryIdx = Helpers.validateIntRange(scanner, 1, surgeryType.length) - 1;
				newService = new Surgery(patientName, fee, surgeryType[surgeryIdx]);
				break;

			case 3:
				System.out.println("Enter test name : ");
				DiagnosticTests[] tests = DiagnosticTests.values();
				for (int i = 0; i < tests.length; i++) {
					System.out.printf("  %d) %s%n", i + 1, tests[i]);
				}
				int testIdx = Helpers.validateIntRange(scanner, 1, tests.length) - 1;
				newService = new DiagnosticTest(patientName, fee, tests[testIdx]);
				break;
			}

			// ✅ Validation check — same pattern as loan eligibility
			if (!newService.isServiceValid()) {
				System.out.println("→ Service rejected: " + newService.getValidationMessage());
				return currentCount;
			}

			services[currentCount] = newService;
			System.out.println("\nService registered successfully.");
			return currentCount + 1;

		} catch (InvalidServiceException e) {
			System.out.println("Registration failed: " + e.getMessage());
			return currentCount;
		}
	}

	private static void displayAllServices(MedicalService[] services, int count) {
		if (count == 0) {
			System.out.println("\nNo services registered in the system.");
			return;
		}

		System.out.println("\n=== All Registered Services ===");
		for (int i = 0; i < count; i++) {
			MedicalService service = services[i];
			System.out.println("Service " + (i + 1) + ":");
			System.out.println("  Type     : " + service.getServiceType());
			System.out.println("  " + service.getServiceInfo());
			System.out.println("  Cost (3 sessions) : ₹" + String.format("%.2f", service.calculateTotalCost(3)));
			System.out.println("  Status   : " + service.getValidationMessage());
			System.out.println("  Hospital : " + MedicalService.getHospitalName());
			System.out.println("----------------------------------------");
		}
	}

}
