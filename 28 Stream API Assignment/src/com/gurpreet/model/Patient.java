package com.gurpreet.model;

import com.gurpreet.enums.Disease;
import com.gurpreet.exception.InvalidBillAmountException;
import com.gurpreet.exception.InvalidIdException;

public class Patient {

	private String patientId;
	private String name;
	private int age;
	private Disease disease;
	private boolean admitted;
	private double billAmount;

	// Parameterized Constructor
	public Patient(String patientId, String name, int age, Disease disease, boolean admitted, double billAmount)
			throws InvalidBillAmountException, InvalidIdException {

		if (patientId == null || patientId.trim().isEmpty()) {
			throw new InvalidIdException("Patient ID cannot be empty.");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		if (age < 0 || age > 120) {
			throw new IllegalArgumentException("Age must be between 0 and 120.");
		}
		if (disease == null) {
			throw new IllegalArgumentException("Disease cannot be empty.");
		}
		if (billAmount < 0) {
			throw new InvalidBillAmountException("Bill amount cannot be negative.");
		}

		this.patientId = patientId.toUpperCase();
		this.name = name;
		this.age = age;
		this.disease = disease;
		this.admitted = admitted;
		this.billAmount = billAmount;
	}

	// Getters
	public String getPatientId() {
		return patientId;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getDisease() {
		return disease.getDisplayName();
	}

	public boolean isAdmitted() {
		return admitted;
	}

	public double getBillAmount() {
		return billAmount;
	}

	@Override
	public String toString() {
		return String.format(
				"Patient ID: %-10s | Name: %-18s | Age: %-3d | Disease: %-15s | Admitted: %-5s | Bill: ₹%.2f",
				patientId, name, age, disease, admitted ? "Yes" : "No", billAmount);
	}
}