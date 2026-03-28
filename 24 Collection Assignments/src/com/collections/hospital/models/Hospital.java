package com.collections.hospital.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import com.collections.hospital.abstracts.Patient;
import com.collections.hospital.comparator.AgeNameComparator;
import com.collections.hospital.enums.BloodGroup;
import com.collections.hospital.enums.Department;
import com.collections.hospital.exceptions.InvalidPatientException;
import com.collections.hospital.utility.Helpers;
import com.collections.hospital.utility.HospitalUtility;


public class Hospital {

    private Set<Patient> patients;                         // HashSet — no duplicate patients
    private Queue<Patient> tokenQueue;                     // LinkedList — FIFO consultation order
    private Map<String, List<Patient>> departmentMap;      // HashMap — department-wise records
    private Scanner scanner;

    public Hospital(Scanner scanner) {
        this.patients = new HashSet<>();
        this.tokenQueue = new LinkedList<>();
        this.departmentMap = new HashMap<>();
        this.scanner = scanner;
    }

    public void registerPatient() throws InvalidPatientException {
        HospitalUtility.displayPatientTypes();
        int choice = Helpers.validateIntRange(scanner, 1, 2);

        Patient patient;
        switch (choice) {
            case 1:
                patient = createGeneralPatient();
                break;
            case 2:
                patient = createEmergencyPatient();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (patients.contains(patient)) {
            throw new InvalidPatientException("Patient already registered with same name and age.");
        }

        patients.add(patient);
        tokenQueue.add(patient);

        // Group by department
        String deptKey = patient.getDepartment();
        if (!departmentMap.containsKey(deptKey)) {
            departmentMap.put(deptKey, new ArrayList<Patient>());
        }
        departmentMap.get(deptKey).add(patient);

        System.out.println("Patient registered! Token ID: " + patient.getId());
    }

    private Patient createGeneralPatient() throws InvalidPatientException {
        System.out.println("Enter Patient Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Age:");
        int age = Helpers.validateIntRange(scanner, 1, 150);

        Department dept = HospitalUtility.printAndGetDepartment(scanner);
        BloodGroup bloodGroup = HospitalUtility.printAndGetBloodGroup(scanner);

        return new GeneralPatient(name, age, dept, bloodGroup);
    }

    private Patient createEmergencyPatient() throws InvalidPatientException {
        System.out.println("Enter Patient Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Age:");
        int age = Helpers.validateIntRange(scanner, 1, 150);

        Department dept = HospitalUtility.printAndGetDepartment(scanner);

        System.out.println("Enter Emergency Reason:");
        String reason = Helpers.validateStringNonEmpty(scanner);

        return new EmergencyPatient(name, age, dept, reason);
    }

    public void viewPatientById(String patientId) throws InvalidPatientException {
        findById(patientId).printDetails();
    }

    public void viewAllByName() throws InvalidPatientException {
        if (patients.isEmpty()) {
            throw new InvalidPatientException("No patients registered.");
        }
        ArrayList<Patient> sorted = new ArrayList<>(patients);
        Collections.sort(sorted); // natural order: by name

        int count = 0;
        for (Patient p : sorted) {
            System.out.println("\nPatient " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    public void viewAllByAge() throws InvalidPatientException {
        if (patients.isEmpty()) {
            throw new InvalidPatientException("No patients registered.");
        }
        ArrayList<Patient> sorted = new ArrayList<>(patients);
        Collections.sort(sorted, new AgeNameComparator()); // custom: older first

        int count = 0;
        for (Patient p : sorted) {
            System.out.println("\nPatient " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    public void callNextPatient() {
        if (tokenQueue.isEmpty()) {
            System.out.println("No patients in queue.");
            return;
        }

        Patient next = tokenQueue.poll();
        System.out.println("Calling next patient:");
        next.printDetails();
    }

    public void viewByDepartment(String deptName) throws InvalidPatientException {
        List<Patient> deptPatients = departmentMap.get(deptName.toUpperCase());

        if (deptPatients == null || deptPatients.isEmpty()) {
            throw new InvalidPatientException("No patients found in department: " + deptName);
        }

        System.out.println("Patients in " + deptName + " department:");
        int count = 0;
        for (Patient p : deptPatients) {
            System.out.println("\nPatient " + (++count) + ":");
            p.printDetails();
            System.out.println("----------------------------");
        }
    }

    // Iterator-based safe removal — discharge a specific patient
    public void dischargePatient(String patientId) throws InvalidPatientException {
        Iterator<Patient> iterator = patients.iterator();
        Patient toDischarge = null;

        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getId().equals(patientId)) {
                toDischarge = p;
                iterator.remove();
                break;
            }
        }

        if (toDischarge == null) {
            throw new InvalidPatientException("Patient not found with ID: " + patientId);
        }

        // Remove from department map
        List<Patient> deptList = departmentMap.get(toDischarge.getDepartment());
        if (deptList != null) {
            deptList.remove(toDischarge);
        }

        System.out.println("Patient discharged successfully:");
        System.out.println(" Name: " + toDischarge.getName());
        System.out.println(" ID  : " + toDischarge.getId());
    }

    private Patient findById(String patientId) throws InvalidPatientException {
        for (Patient p : patients) {
            if (p.getId().equals(patientId)) {
                return p;
            }
        }
        throw new InvalidPatientException("Patient not found with ID: " + patientId);
    }
}