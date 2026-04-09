package com.collections.hospital.test;

import java.util.Scanner;

import com.collections.hospital.models.Helpers;
import com.collections.hospital.models.Hospital;
import com.collections.hospital.models.HospitalUtility;
import com.collections.hospital.models.InvalidPatientException;

public class HospitalManagementTest {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Hospital hospital = new Hospital(scanner);

            System.out.println("-----------------------------------");
            System.out.println("  Hospital Patient Token System");
            System.out.println("-----------------------------------\n");

            boolean isRun = true;
            while (isRun) {
                try {
                    HospitalUtility.displayMenu();
                    int choice = Helpers.validateIntRange(scanner, 1, 8);

                    switch (choice) {

                        case 1:
                            hospital.registerPatient();
                            break;

                        case 2:
                            String patientId = Helpers.validatePatientId(scanner);
                            hospital.viewPatientById(patientId);
                            break;

                        case 3:
                            hospital.viewAllByName();
                            break;

                        case 4:
                            hospital.viewAllByAge();
                            break;

                        case 5:
                            hospital.callNextPatient();
                            break;

                        case 6:
                            System.out.println("Enter Department (CARDIOLOGY / ORTHOPEDICS / NEUROLOGY / GENERAL / EMERGENCY):");
                            String dept = Helpers.validateStringNonEmpty(scanner);
                            hospital.viewByDepartment(dept);
                            break;

                        case 7:
                            patientId = Helpers.validatePatientId(scanner);
                            hospital.dischargePatient(patientId);
                            break;

                        case 8:
                            isRun = false;
                            System.out.println("Goodbye!");
                            break;

                        default:
                            System.out.println("Enter valid input.");
                    }

                } catch (InvalidPatientException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}