package com.gurpreet.comparator.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.comparator.comparators.NameAgeComparator;
import com.gurpreet.comparator.models.Candidate;
import com.gurpreet.helpers.Helpers;

public class CandidateTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Candidate> candidates = new ArrayList<>();

        System.out.println("=========================================");
        System.out.println("     Candidate List - User Input");
        System.out.println("=========================================");

        System.out.println("How many candidates do you want to add? ");
        int n = Helpers.validateIntNonNegative(scanner);

        for (int i = 1; i <= n; i++) {
            System.out.println("\nCandidate #" + i + ":");
            System.out.println("Enter name: ");
            String name = Helpers.validateStringNonEmpty(scanner);
            System.out.println("Enter age: ");
            int age = Helpers.validateIntNonNegative(scanner);
            candidates.add(new Candidate(name, age));
        }

        System.out.println("\nBefore sorting:");
        printCandidates(candidates);

        Collections.sort(candidates, new NameAgeComparator());

        System.out.println("\nAfter sorting (by Name → Age):");
        printCandidates(candidates);

        scanner.close();
    }


    private static void printCandidates(List<Candidate> list) {
        System.out.println("Name                  Age");
        System.out.println("---------------------------");
        for (Candidate candidate : list) {
            System.out.printf("%-20s %d%n", candidate.getName(), candidate.getAge());
        }
    }
}