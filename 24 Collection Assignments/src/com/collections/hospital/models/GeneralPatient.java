package com.collections.hospital.models;

public class GeneralPatient extends Patient {

    private static long counter = 1000;
    private static final String PREFIX = "GP";

    private BloodGroup bloodGroup;

    public GeneralPatient(String name, int age, Department department, BloodGroup bloodGroup)
            throws InvalidPatientException {

        super(name, age, department, PREFIX, counter++);

        if (bloodGroup == null) {
            throw new InvalidPatientException("Blood group can't be null");
        }

        this.bloodGroup = bloodGroup;
    }

    @Override
    public void printDetails() {
        System.out.println(" Patient ID   : " + getId());
        System.out.println(" Type         : General Patient");
        System.out.println(" Name         : " + getName());
        System.out.println(" Age          : " + getAge());
        System.out.println(" Department   : " + getDepartment());
        System.out.println(" Blood Group  : " + bloodGroup);
    }
}