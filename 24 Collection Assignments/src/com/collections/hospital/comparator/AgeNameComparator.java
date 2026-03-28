package com.collections.hospital.comparator;

import java.util.Comparator;

import com.collections.hospital.abstracts.Patient;

public class AgeNameComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient p1, Patient p2) {
        // Descending age
        int ageResult = Integer.compare(p2.getAge(), p1.getAge());
        if (ageResult != 0) return ageResult;
        // Then alphabetical name
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}