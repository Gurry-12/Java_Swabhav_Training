package com.collections.student.test;

import java.util.Scanner;

import com.collections.student.models.Helpers;
import com.collections.student.models.InvalidStudentException;
import com.collections.student.models.StudentTracker;
import com.collections.student.models.StudentUtility;

public class StudentTrackerTest {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            StudentTracker tracker = new StudentTracker(scanner);

            System.out.println("-----------------------------------");
            System.out.println("   Student Performance Tracker");
            System.out.println("-----------------------------------\n");

            boolean isRun = true;
            while (isRun) {
                try {
                    StudentUtility.displayMenu();
                    int choice = Helpers.validateIntRange(scanner, 1, 8);

                    switch (choice) {

                        case 1:
                            tracker.addStudent();
                            break;

                        case 2:
                            String studentId = Helpers.validateStudentId(scanner);
                            tracker.addMarks(studentId);
                            break;

                        case 3:
                            studentId = Helpers.validateStudentId(scanner);
                            tracker.viewStudentById(studentId);
                            break;

                        case 4:
                            tracker.viewAllStudents();
                            break;

                        case 5:
                            tracker.viewRankings();
                            break;

                        case 6:
                            System.out.println("Enter Department (CS/IT/MECHANICAL/CIVIL/ELECTRICAL):");
                            String dept = Helpers.validateStringNonEmpty(scanner);
                            tracker.viewByDepartment(dept);
                            break;

                        case 7:
                            tracker.removeIneligibleStudents();
                            break;

                        case 8:
                            isRun = false;
                            System.out.println("Goodbye!");
                            break;

                        default:
                            System.out.println("Enter valid input.");
                    }

                } catch (InvalidStudentException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}