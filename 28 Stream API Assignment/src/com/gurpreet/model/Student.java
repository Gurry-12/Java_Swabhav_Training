package com.gurpreet.model;

import com.gurpreet.enums.Section;
import com.gurpreet.exception.InvalidMarksException;

public class Student {

	private int rollNo;
	private String name;
	private int standard;
	private double marks;
	private Section section;

	// Parameterized Constructor
	public Student(int rollNo, String name, int standard, double marks, Section section) throws InvalidMarksException {

		if (rollNo <= 0) {
			throw new IllegalArgumentException("Roll number must be positive.");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		if (standard < 1 || standard > 12) {
			throw new IllegalArgumentException("Standard must be between 1 and 12.");
		}
		if (marks < 0 || marks > 100) {
			throw new InvalidMarksException("Marks must be between 0 and 100.");
		}
		if (section == null ) {
			throw new IllegalArgumentException("Section cannot be empty.");
		}

		this.rollNo = rollNo;
		this.name = name;
		this.standard = standard;
		this.marks = marks;
		this.section = section;
	}

	// Getters
	public int getRollNo() {
		return rollNo;
	}

	public String getName() {
		return name;
	}

	public int getStandard() {
		return standard;
	}

	public double getMarks() {
		return marks;
	}

	public String getSection() {
		return section.name();
	}

	@Override
	public String toString() {
		return String.format("Roll No: %-6d | Name: %-18s | Std: %-2d | Section: %-3s | Marks: %6.1f", rollNo, name,
				standard, section, marks);
	}
}