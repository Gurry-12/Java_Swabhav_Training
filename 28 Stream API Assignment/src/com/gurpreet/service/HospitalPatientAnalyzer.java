package com.gurpreet.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.gurpreet.enums.Disease;
import com.gurpreet.exception.InvalidBillAmountException;
import com.gurpreet.exception.InvalidIdException;
import com.gurpreet.model.Patient;
import com.gurpreet.utility.Helpers;

public class HospitalPatientAnalyzer {

	public void getAllAdmittedPatients(List<Patient> patients) {
		System.out.println("\n--- All Admitted Patients ---");
		List<Patient> admitted = patients.stream().filter(Patient::isAdmitted).collect(Collectors.toList());

		if (admitted.isEmpty()) {
			System.out.println("No admitted patients found.");
			return;
		}
		admitted.forEach(System.out::println);
	}

	public void groupPatientsByDisease(List<Patient> patients) {
		System.out.println("\n--- Patients Grouped by Disease ---");
		Map<String, List<Patient>> grouped = patients.stream().collect(Collectors.groupingBy(Patient::getDisease));

		grouped.forEach((disease, list) -> {
			System.out.println("\nDisease: " + disease);
			list.forEach(System.out::println);
		});
	}

	public void countAdmittedVsNonAdmitted(List<Patient> patients) {
		System.out.println("\n--- Admitted vs Non-Admitted Patients ---");
		Map<Boolean, Long> countMap = patients.stream()
				.collect(Collectors.partitioningBy(Patient::isAdmitted, Collectors.counting()));

		System.out.println("Admitted Patients     : " + countMap.getOrDefault(true, 0L));
		System.out.println("Non-Admitted Patients : " + countMap.getOrDefault(false, 0L));
	}

	public void findPatientWithHighestBill(List<Patient> patients) {
		System.out.println("\n--- Patient with Highest Bill Amount ---");
		Optional<Patient> highestBillPatient = patients.stream()
				.max(Comparator.comparingDouble(Patient::getBillAmount));

		if (highestBillPatient.isPresent()) {
			System.out.println(highestBillPatient.get());
		} else {
			System.out.println("No patients found.");
		}
	}

	public void calculateAverageBillAmount(List<Patient> patients) {
		System.out.println("\n--- Average Bill Amount ---");
		if (patients.isEmpty()) {
			System.out.println("No patients found.");
			return;
		}
		double average = patients.stream().collect(Collectors.averagingDouble(Patient::getBillAmount));

		System.out.printf("Average Bill Amount : ₹%.2f%n", average);
	}

	public void getSeniorPatientsNames(List<Patient> patients) {
		System.out.println("\n--- Patients Above Age 60 ---");
		List<String> seniorNames = patients.stream().filter(p -> p.getAge() > 60).map(Patient::getName)
				.collect(Collectors.toList());

		if (seniorNames.isEmpty()) {
			System.out.println("No patients above 60 years.");
			return;
		}
		for (int i = 0; i < seniorNames.size(); i++) {
			System.out.println((i + 1) + ". " + seniorNames.get(i));
		}
	}

	public void getDiseaseToPatientNamesMap(List<Patient> patients) {
		System.out.println("\n--- Disease to Patient Names Map ---");
		Map<String, List<String>> diseaseMap = patients.stream().collect(
				Collectors.groupingBy(Patient::getDisease, Collectors.mapping(Patient::getName, Collectors.toList())));

		diseaseMap.forEach((disease, names) -> {
			System.out.println(disease + " : " + names);
		});
	}

	public void displayAllPatients(List<Patient> patients) {
		System.out.println("\n--- All Patients ---");
		if (patients.isEmpty()) {
			System.out.println("No patients in the system.");
		} else {
			patients.forEach(System.out::println);
		}
	}

	// Add New Patient
	public void addNewPatient(Scanner scanner, List<Patient> patients) {
		System.out.println("\n=== Add New Patient ===");

		try {
			String patientId = getValidPatientId(scanner, patients);
			String name = getValidName(scanner);
			int age = getValidAge(scanner);
			Disease disease = getValidDisease(scanner);
			boolean admitted = getValidAdmittedStatus(scanner);
			double billAmount = getValidBillAmount(scanner);

			Patient newPatient = new Patient(patientId, name, age, disease, admitted, billAmount);
			patients.add(newPatient);

			System.out.println("\nPatient record added successfully!");
			System.out.println(newPatient);

		} catch (InvalidIdException |InvalidBillAmountException e) {
			System.out.println("Error: " + ((Throwable) e).getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
	}

	// Helper Methods
	private String getValidPatientId(Scanner scanner, List<Patient> patients) {
		while (true) {
			System.out.print("Enter Patient ID (e.g. PAT123): ");
			String id = scanner.nextLine().trim().toUpperCase();
			if (id.isEmpty()) {
				System.out.println("Patient ID cannot be empty.");
				continue;
			}
			if (patients.stream().anyMatch(p -> p.getPatientId().equals(id))) {
				System.out.println("Patient ID already exists.");
				continue;
			}
			return id;
		}
	}

	private String getValidName(Scanner scanner) {
		System.out.print("Enter Patient Name: ");
		return Helpers.validateStringLettersOnly(scanner);
	}

	private int getValidAge(Scanner scanner) {
		System.out.print("Enter Age: ");
		return Helpers.validateIntRange(scanner, 0, 120);
	}

	private Disease getValidDisease(Scanner scanner) {
		Disease[] diseases = Disease.values();

		System.out.println("Select Disease:");
		for (int i = 0; i < diseases.length; i++) {
			System.out.println((i + 1) + " : " + diseases[i]);
		}
		int choice = Helpers.validateIntRange(scanner, 1, diseases.length);
		return diseases[choice - 1];
	}

	private boolean getValidAdmittedStatus(Scanner scanner) {
		while (true) {
			System.out.print("Is patient admitted? (yes/no): ");
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.equals("yes") || input.equals("y"))
				return true;
			if (input.equals("no") || input.equals("n"))
				return false;
			System.out.println("Please enter yes or no.");
		}
	}

	private double getValidBillAmount(Scanner scanner) {
		System.out.print("Enter Bill Amount (₹): ");
		return Helpers.validateDoublePositive(scanner);
	}
}