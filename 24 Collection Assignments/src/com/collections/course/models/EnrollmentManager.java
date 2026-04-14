package com.collections.course.models;

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

public class EnrollmentManager {

    private Set<Participant> participants;                    // HashSet — no duplicate enrollments
    private Queue<Participant> waitingList;                   // LinkedList — waiting list queue
    private Map<String, List<Participant>> batchMap;          // HashMap — batch-wise grouping
    private Scanner scanner;

    public EnrollmentManager(Scanner scanner) {
        this.participants = new HashSet<>();
        this.waitingList = new LinkedList<>();
        this.batchMap = new HashMap<>();
        this.scanner = scanner;
    }

    public void enroll() throws InvalidParticipantException {
        EnrollmentUtility.displayParticipantTypes();
        int choice = Helpers.validateIntRange(scanner, 1, 2);

        Participant participant;
        switch (choice) {
            case 1:
                participant = createRegularParticipant();
                break;
            case 2:
                participant = createCorporateParticipant();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (participants.contains(participant)) {
            throw new InvalidParticipantException(
                "Participant already enrolled with same name and track.");
        }

        participants.add(participant);
        waitingList.add(participant);

        // Batch-wise grouping
        String batchKey = participant.getBatchName().toLowerCase();
        if (!batchMap.containsKey(batchKey)) {
            batchMap.put(batchKey, new ArrayList<Participant>());
        }
        batchMap.get(batchKey).add(participant);

        System.out.println("Participant enrolled! ID: " + participant.getId()
            + " | Added to waiting list.");
    }

    private Participant createRegularParticipant() throws InvalidParticipantException {
    	String id = Helpers.validateParticipantId(scanner);
    	
        System.out.println("Enter Participant Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        Track track = EnrollmentUtility.printAndGetTrack(scanner);

        System.out.println("Enter Batch Name:");
        String batch = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Education Level (e.g. B.Tech, MCA):");
        String education = Helpers.validateStringNonEmpty(scanner);

        return new RegularParticipant(id, name, track, batch, education);
    }

    private Participant createCorporateParticipant() throws InvalidParticipantException {
    	String id = Helpers.validateParticipantId(scanner);
        System.out.println("Enter Participant Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        Track track = EnrollmentUtility.printAndGetTrack(scanner);

        System.out.println("Enter Batch Name:");
        String batch = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Company Name:");
        String company = Helpers.validateStringNonEmpty(scanner);

        return new CorporateParticipant(id, name, track, batch, company);
    }

    public void viewById(String participantId) throws InvalidParticipantException {
        findById(participantId).printDetails();
    }

    public void viewAllByName() throws InvalidParticipantException {
        if (participants.isEmpty()) {
            throw new InvalidParticipantException("No participants enrolled.");
        }
        ArrayList<Participant> sorted = new ArrayList<>(participants);
        Collections.sort(sorted); // natural: by name

        int count = 0;
        for (Participant p : sorted) {
            System.out.println("\nParticipant " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    public void viewAllByBatch() throws InvalidParticipantException {
        if (participants.isEmpty()) {
            throw new InvalidParticipantException("No participants enrolled.");
        }
        ArrayList<Participant> sorted = new ArrayList<>(participants);
        Collections.sort(sorted, new BatchNameComparator()); // custom: by batch then name

        int count = 0;
        for (Participant p : sorted) {
            System.out.println("\nParticipant " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    // Admit the next participant from the waiting list
    public void admitNext() {
        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return;
        }
        Participant next = waitingList.poll();
        System.out.println("Admitting next participant from waiting list:");
        next.printDetails();
    }

    public void viewByBatch(String batchName) throws InvalidParticipantException {
        List<Participant> batchParticipants = batchMap.get(batchName.toLowerCase());

        if (batchParticipants == null || batchParticipants.isEmpty()) {
            throw new InvalidParticipantException("No participants found in batch: " + batchName);
        }

        System.out.println("Participants in batch: " + batchName);
        int count = 0;
        for (Participant p : batchParticipants) {
            System.out.println("\nParticipant " + (++count) + ":");
            p.printDetails();
            System.out.println("----------------------------");
        }
    }

    // Iterator-based safe removal — withdraw a participant
    public void withdraw(String participantId) throws InvalidParticipantException {
        Iterator<Participant> iterator = participants.iterator();
        Participant toRemove = null;

        while (iterator.hasNext()) {
            Participant p = iterator.next();
            if (p.getId().equals(participantId)) {
                toRemove = p;
                iterator.remove();
                break;
            }
        }

        if (toRemove == null) {
            throw new InvalidParticipantException("Participant not found with ID: " + participantId);
        }

        // Clean up batch map
        List<Participant> batchList = batchMap.get(toRemove.getBatchName().toLowerCase());
        if (batchList != null) {
            batchList.remove(toRemove);
        }

        System.out.println("Participant withdrawn successfully:");
        System.out.println(" Name : " + toRemove.getName());
        System.out.println(" ID   : " + toRemove.getId());
    }

    private Participant findById(String participantId) throws InvalidParticipantException {
        for (Participant p : participants) {
            if (p.getId().equals(participantId)) {
                return p;
            }
        }
        throw new InvalidParticipantException("Participant not found with ID: " + participantId);
    }
}