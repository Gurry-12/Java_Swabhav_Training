package com.gurpreet.oopsassignment.models;

public class ScholarshipStudent extends Student {
    private double scholarshipAmount;

    // Constructor chaining
    public ScholarshipStudent(String studentName, String enrolledCourse, double scholarshipAmount) {
        super(studentName, enrolledCourse);
        if (scholarshipAmount < 0) {
            throw new IllegalArgumentException("Scholarship amount cannot be negative.");
        }
        this.scholarshipAmount = scholarshipAmount;
    }

    @Override
    public void processProfile() {
        System.out.println("ID: " + getStudentId() + ", Name: " + getStudentName() + 
                          ", Course: " + getEnrolledCourse() + ", Type: Scholarship, Amount: $" + scholarshipAmount);
    }
}

