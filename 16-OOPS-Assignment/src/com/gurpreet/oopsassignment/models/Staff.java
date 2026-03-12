package com.gurpreet.oopsassignment.models;

import com.gurpreet.oopsassignment.enums.Department;

public abstract class Staff {
	private String staffId;
	private String staffName;
	private Department department;
	private final static String prefix = "S";
	private static long staffCounter = 0;

	public Staff(String staffName, Department department) {
		
		if (staffName == null || staffName.trim().isEmpty()) {
			throw new IllegalArgumentException("Staff name must not be null or empty.");
		}
		if (department == null) {
			throw new IllegalArgumentException("Department must not be null.");
		}
		this.staffId = prefix + String.format("%04d", ++staffCounter);
		this.staffName = staffName;
		this.department = department;
	}

	public String getStaffId() {
		return staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public Department getDepartment() {
		return department;
	}

	public abstract void printRecord();
}

