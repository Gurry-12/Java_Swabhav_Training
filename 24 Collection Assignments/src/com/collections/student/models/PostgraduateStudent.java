package com.collections.student.models;

public class PostgraduateStudent extends Student {

    private static long counter = 5000;
    private static final String PREFIX = "PG";

    private String researchTopic;

    public PostgraduateStudent(String name, Department department, String researchTopic)
            throws InvalidStudentException {

        super(name, department, PREFIX, counter++);

        if (researchTopic == null || researchTopic.trim().isEmpty()) {
            throw new InvalidStudentException("Research topic can't be empty");
        }

        this.researchTopic = researchTopic;
    }

    @Override
    public void printDetails() {
        System.out.println(" Student ID      : " + getId());
        System.out.println(" Type            : Postgraduate");
        System.out.println(" Name            : " + getName());
        System.out.println(" Department      : " + getDepartment());
        System.out.println(" Research Topic  : " + researchTopic);
        System.out.println(" Total Marks     : " + getTotalMarks());
        System.out.printf (" Average Marks   : %.2f%n", getAverageMarks());
        if (!getSubjectMarks().isEmpty()) {
            System.out.println(" Subject Marks   : " + getSubjectMarks());
        }
    }
}