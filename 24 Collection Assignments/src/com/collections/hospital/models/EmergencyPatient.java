package com.collections.hospital.models;

public class EmergencyPatient extends Patient {

    private static long counter = 9000;
    private static final String PREFIX = "EP";

    private String reason;

    public EmergencyPatient(String name, int age, Department department, String reason)
            throws InvalidPatientException {

        super(name, age, department, PREFIX, counter++);

        if (reason == null || reason.trim().isEmpty()) {
            throw new InvalidPatientException("Emergency reason can't be empty");
        }

        this.reason = reason;
    }

    @Override
    public void printDetails() {
        System.out.println(" Patient ID      : " + getId());
        System.out.println(" Type            : Emergency Patient");
        System.out.println(" Name            : " + getName());
        System.out.println(" Age             : " + getAge());
        System.out.println(" Department      : " + getDepartment());
        System.out.println(" Emergency Reason: " + reason);
    }
}