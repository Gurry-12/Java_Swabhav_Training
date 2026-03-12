package com.gurpreet.oopsassignment.models;

public abstract class Student {
	
	private static long studentCounter = 0;
    private String studentId;
    private String studentName;
    private String enrolledCourse;
	private final static String prefix = "S";

    // Constructor with validation
    public Student(String studentName, String enrolledCourse) {
        
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name must not be null or empty.");
        }
        if (enrolledCourse == null || enrolledCourse.trim().isEmpty()) {
            throw new IllegalArgumentException("Enrolled course must not be null or empty.");
        }
        this.studentId = prefix  + String.format("%04d", ++studentCounter);
        this.studentName = studentName;
        this.enrolledCourse = enrolledCourse;
    }

    // Getters for encapsulation
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEnrolledCourse() {
        return enrolledCourse;
    }

    // Abstract method for polymorphic processing
    public abstract void processProfile();
}

