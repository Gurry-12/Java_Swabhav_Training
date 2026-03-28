package com.collections.course.test;

import java.util.Scanner;

import com.collections.course.exceptions.InvalidParticipantException;
import com.collections.course.models.EnrollmentManager;
import com.collections.course.utility.EnrollmentUtility;
import com.collections.course.utility.Helpers;

public class EnrollmentManagementTest {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            EnrollmentManager manager = new EnrollmentManager(scanner);

            System.out.println("-----------------------------------");
            System.out.println("  Course Enrollment & Waiting List");
            System.out.println("-----------------------------------\n");

            boolean isRun = true;
            while (isRun) {
                try {
                    EnrollmentUtility.displayMenu();
                    int choice = Helpers.validateIntRange(scanner, 1, 8);

                    switch (choice) {

                        case 1:
                            manager.enroll();
                            break;

                        case 2:
                            String participantId = Helpers.validateParticipantId(scanner);
                            manager.viewById(participantId);
                            break;

                        case 3:
                            manager.viewAllByName();
                            break;

                        case 4:
                            manager.viewAllByBatch();
                            break;

                        case 5:
                            manager.admitNext();
                            break;

                        case 6:
                            System.out.println("Enter Batch Name:");
                            String batch = Helpers.validateStringNonEmpty(scanner);
                            manager.viewByBatch(batch);
                            break;

                        case 7:
                            participantId = Helpers.validateParticipantId(scanner);
                            manager.withdraw(participantId);
                            break;

                        case 8:
                            isRun = false;
                            System.out.println("Goodbye!");
                            break;

                        default:
                            System.out.println("Enter valid input.");
                    }

                } catch (InvalidParticipantException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
