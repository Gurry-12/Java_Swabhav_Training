package com.collections.student.models;

import com.collections.student.abstracts.Student;
import com.collections.student.enums.Department;
import com.collections.student.exceptions.InvalidStudentException;

public class UndergraduateStudent extends Student {

    private static long counter = 1000;
    private static final String PREFIX = "UG";

    private int enrollmentYear;

    public UndergraduateStudent(String name, Department department, int enrollmentYear)
            throws InvalidStudentException {

        super(name, department, PREFIX, counter++);

        if (enrollmentYear < 2000 || enrollmentYear > 2026) {
            throw new InvalidStudentException("Invalid enrollment year");
        }

        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public void printDetails() {
        System.out.println(" Student ID      : " + getId());
        System.out.println(" Type            : Undergraduate");
        System.out.println(" Name            : " + getName());
        System.out.println(" Department      : " + getDepartment());
        System.out.println(" Enrollment Year : " + enrollmentYear);
        System.out.println(" Total Marks     : " + getTotalMarks());
        System.out.printf (" Average Marks   : %.2f%n", getAverageMarks());
        if (!getSubjectMarks().isEmpty()) {
            System.out.println(" Subject Marks   : " + getSubjectMarks());
        }
    }
}