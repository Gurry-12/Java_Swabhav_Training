package com.gurpreet.oopsassignment.models;

import com.gurpreet.oopsassignment.enums.Department;
import com.gurpreet.oopsassignment.enums.Specialization;

public class Doctor extends Staff {
	private Specialization specialization;
	private int practiceYears;

	public Doctor(String staffName, Department department, Specialization specialization, int practiceYears) {
		super(staffName, department);
		if (specialization == null) {
			throw new IllegalArgumentException("Specialization must not be null.");
		}
		if (practiceYears < 0) {
			throw new IllegalArgumentException("Practice years must be non-negative.");
		}
		this.specialization = specialization;
		this.practiceYears = practiceYears;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public int getPracticeYears() {
		return practiceYears;
	}

	@Override
	public void printRecord() {
		System.out.println("ID: " + getStaffId() + ", Name: " + getStaffName() + ", Dept: " + getDepartment()
				+ ", Spec: " + specialization + ", Years: " + practiceYears);
	}
}

