package com.gurpreet.oopsassignment.models;

import com.gurpreet.oopsassignment.enums.Department;
import com.gurpreet.oopsassignment.enums.ShiftType;

public class Nurse extends Staff {
	private ShiftType shiftType;

	public Nurse(String staffName, Department department, ShiftType shiftType) {
		super(staffName, department);
		if (shiftType == null) {
			throw new IllegalArgumentException("Shift type must not be null.");
		}
		this.shiftType = shiftType;
	}

	public ShiftType getShiftType() {
		return shiftType;
	}

	@Override
	public void printRecord() {
		System.out.println("ID: " + getStaffId() + ", Name: " + getStaffName() + ", Dept: " + getDepartment()
				+ ", Shift: " + shiftType);
	}
}