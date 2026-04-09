package com.collections.course.models;

import java.util.Scanner;

public class EnrollmentUtility {

    public static void displayParticipantTypes() {
        System.out.println("Select Participant Type:");
        System.out.println("1. Regular Participant");
        System.out.println("2. Corporate Participant");
    }

    public static Track printAndGetTrack(Scanner scanner) {
        Track[] tracks = Track.values();
        System.out.println("Select Track:");
        for (int i = 0; i < tracks.length; i++) {
            System.out.println((i + 1) + " : " + tracks[i]);
        }
        int choice = Helpers.validateIntRange(scanner, 1, tracks.length);
        return tracks[choice - 1];
    }

    public static void displayMenu() {
        System.out.println("\n====== Course Enrollment System ======");
        System.out.println("1. Enroll Participant");
        System.out.println("2. View Participant by ID");
        System.out.println("3. View All Participants (sorted by name)");
        System.out.println("4. View All Participants (sorted by batch)");
        System.out.println("5. Admit Next from Waiting List");
        System.out.println("6. View Participants by Batch");
        System.out.println("7. Withdraw Participant");
        System.out.println("8. Exit");
        System.out.println("======================================");
    }
}