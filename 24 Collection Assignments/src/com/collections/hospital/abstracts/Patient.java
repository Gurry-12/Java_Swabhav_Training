package com.collections.hospital.abstracts;

import java.util.Objects;

import com.collections.hospital.enums.Department;
import com.collections.hospital.exceptions.InvalidPatientException;

public abstract class Patient implements Comparable<Patient> {

    private String id;
    private String name;
    private int age;
    private Department department;

    public Patient(String name, int age, Department department, String prefix, long counter)
            throws InvalidPatientException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidPatientException("Patient name can't be empty");
        }
        if (age <= 0 || age > 150) {
            throw new InvalidPatientException("Age must be between 1 and 150");
        }
        if (department == null) {
            throw new InvalidPatientException("Department can't be null");
        }

        this.id = prefix + counter;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getId()          { return id; }
    public String getName()        { return name; }
    public int getAge()            { return age; }
    public String getDepartment()  { return department.toString(); }

    // Natural ordering: by name alphabetically
    @Override
    public int compareTo(Patient other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // Duplicate: same name + same age
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Patient)) return false;
        Patient other = (Patient) obj;
        return other.name.equalsIgnoreCase(name) && other.age == age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), age);
    }

    public abstract void printDetails();
}