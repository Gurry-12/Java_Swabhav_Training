package com.gurpreet.oopsassignment.models;

public class RegularStudent extends Student {
    private double baseFee;

    // Constructor chaining
    public RegularStudent(String studentName, String enrolledCourse, double baseFee) {
        super(studentName, enrolledCourse);
        if (baseFee < 0) {
            throw new IllegalArgumentException("Base fee cannot be negative.");
        }
        this.baseFee = baseFee;
    }

    @Override
    public void processProfile() {
        System.out.println("ID: " + getStudentId() + ", Name: " + getStudentName() + 
                          ", Course: " + getEnrolledCourse() + ", Type: Regular, Fee: $" + baseFee);
    }
}

