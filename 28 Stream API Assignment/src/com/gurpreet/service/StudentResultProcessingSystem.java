package com.gurpreet.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.gurpreet.enums.Section;
import com.gurpreet.exception.InvalidMarksException;
import com.gurpreet.model.Student;
import com.gurpreet.utility.Helpers;

public class StudentResultProcessingSystem {

    private static final double PASSING_MARKS = 40.0;
    private static final double FULL_MARKS = 100.0;

    public void findPassedStudents(List<Student> students) {
        System.out.println("\n--- Students Who Passed (Marks >= " + PASSING_MARKS + ") ---");
        List<Student> passed = students.stream()
                .filter(s -> s.getMarks() >= PASSING_MARKS)
                .collect(Collectors.toList());

        if (passed.isEmpty()) {
            System.out.println("No students passed.");
            return;
        }
        passed.forEach(System.out::println);
    }

    public void getTop3Students(List<Student> students) {
        System.out.println("\n--- Top 3 Students by Marks ---");
        List<Student> top3 = students.stream()
                .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
                .limit(3)
                .collect(Collectors.toList());

        if (top3.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (int i = 0; i < top3.size(); i++) {
            System.out.println((i + 1) + ". " + top3.get(i));
        }
    }

    public void groupStudentsBySection(List<Student> students) {
        System.out.println("\n--- Students Grouped by Section ---");
        Map<String, List<Student>> grouped = students.stream()
                .collect(Collectors.groupingBy(Student::getSection));

        grouped.forEach((section, list) -> {
            System.out.println("\nSection " + section + ":");
            list.forEach(System.out::println);
        });
    }

    public void countStudentsSectionWise(List<Student> students) {
        System.out.println("\n--- Section-wise Student Count ---");
        Map<String, Long> countMap = students.stream()
                .collect(Collectors.groupingBy(Student::getSection, Collectors.counting()));

        countMap.forEach((section, count) ->
                System.out.println("Section " + section + " : " + count + " student(s)"));
    }

    public void getAverageMarksSectionWise(List<Student> students) {
        System.out.println("\n--- Average Marks Section-wise ---");
        Map<String, Double> avgMap = students.stream()
                .collect(Collectors.groupingBy(Student::getSection,
                        Collectors.averagingDouble(Student::getMarks)));

        avgMap.forEach((section, avg) ->
                System.out.printf("Section %-3s : %.2f%n", section, avg));
    }

    public void getAllNamesInUppercase(List<Student> students) {
        System.out.println("\n--- Student Names in Uppercase ---");
        List<String> upperNames = students.stream()
                .map(s -> s.getName().toUpperCase())
                .collect(Collectors.toList());

        for (int i = 0; i < upperNames.size(); i++) {
            System.out.println((i + 1) + ". " + upperNames.get(i));
        }
    }

    public void checkAnyFullMarks(List<Student> students) {
        System.out.println("\n--- Full Marks Check ---");
        boolean hasFullMarks = students.stream()
                .anyMatch(s -> s.getMarks() == FULL_MARKS);

        if (hasFullMarks) {
            System.out.println("Yes, at least one student scored full marks (100).");
        } else {
            System.out.println("No student scored full marks.");
        }
    }

    public void displayAllStudents(List<Student> students) {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            students.forEach(System.out::println);
        }
    }

    // Add New Student
    public void addNewStudent(Scanner scanner, List<Student> students) {
        System.out.println("\n=== Add New Student ===");

        try {
            int rollNo = getValidRollNo(scanner, students);
            String name = getValidName(scanner);
            int standard = getValidStandard(scanner);
            double marks = getValidMarks(scanner);
            Section section = getValidSection(scanner);

            Student student = new Student(rollNo, name, standard, marks, section);
            students.add(student);

            System.out.println("\nStudent added successfully!");
            System.out.println(student);

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // Helper Methods
    private int getValidRollNo(Scanner scanner, List<Student> students) {
        while (true) {
            System.out.print("Enter Roll Number: ");
            int roll = Helpers.validateIntPositive(scanner);
            if (students.stream().anyMatch(s -> s.getRollNo() == roll)) {
                System.out.println("Roll number already exists. Please enter unique roll number.");
                continue;
            }
            return roll;
        }
    }

    private String getValidName(Scanner scanner) {
        System.out.print("Enter Student Name: ");
        return Helpers.validateStringLettersOnly(scanner);
    }

    private int getValidStandard(Scanner scanner) {
        while (true) {
            System.out.print("Enter Standard (1-12): ");
            int std = Helpers.validateIntRange(scanner, 1, 12);
            return std;
        }
    }

    private double getValidMarks(Scanner scanner) {
        System.out.print("Enter Marks (0-100): ");
        return Helpers.validateDoubleNonNegative(scanner); // Further validation in constructor
    }

    private Section getValidSection(Scanner scanner) {
    	Section[] sections = Section.values();

		System.out.println("Select Section:");
		for (int i = 0; i < sections.length; i++) {
			System.out.println((i + 1) + " : " + sections[i]);
		}
		int choice = Helpers.validateIntRange(scanner, 1, sections.length);
		return sections[choice - 1];
    }
}