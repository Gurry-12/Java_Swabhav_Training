package com.gurpreet.streamio.models;

public class Student {

	private static long counter = 10000l;
	private String id;
	private String name;
	private long rollNumber;
	private String course;

	public Student(String name, long rollNumber, String course) {

		if (name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}

		if (rollNumber < 0) {
			throw new IllegalArgumentException("Roll number can not be negative");
		}

		if (course.isEmpty()) {
			throw new IllegalArgumentException("Course can not be empty");
		}

		this.id = "S" + ++counter;
		this.name = name;
		this.rollNumber = rollNumber;
		this.course = course;

	}

	public Student(String id2, String name2, long rollNumber2, String course2) {
		this.id = id2;
		this.name = name2;
		this.rollNumber = rollNumber2;
		this.course = course2;
		
	}

	public String getData() {
		String resultData = "ID: " + this.id + ", Name: " + this.name + ", Roll Number: " + this.rollNumber
				+ ", Course: " + this.course;
		return resultData;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public static Student fromFile(String fileLine) {
	    if (fileLine == null || fileLine.trim().isEmpty()) {
	        throw new IllegalArgumentException("Invalid or empty line");
	    }

	    String[] parts = fileLine.split(",\\s*");  // split on comma + optional spaces

	    if (parts.length != 4) {
	        throw new IllegalArgumentException("Expected 4 fields, found " + parts.length);
	    }

	    String idPart   = parts[0].split(":\\s*", 2)[1].trim();
	    String namePart = parts[1].split(":\\s*", 2)[1].trim();
	    String rollPart = parts[2].split(":\\s*", 2)[1].trim();
	    String coursePart = parts[3].split(":\\s*", 2)[1].trim();

	    long rollNumber;
	    try {
	        rollNumber = Long.parseLong(rollPart);
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Invalid roll number format: " + rollPart);
	    }

	    return new Student(idPart, namePart, rollNumber, coursePart);
	}



}
