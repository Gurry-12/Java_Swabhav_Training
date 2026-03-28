package com.collections.student.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.collections.student.abstracts.Student;
import com.collections.student.comparator.TotalMarksComparator;
import com.collections.student.enums.Department;
import com.collections.student.exceptions.InvalidStudentException;
import com.collections.student.utility.Helpers;
import com.collections.student.utility.StudentUtility;

public class StudentTracker {

    private Set<Student> students;                         // HashSet — no duplicate students
    private Map<String, List<Student>> departmentMap;      // HashMap — department-wise grouping
    private Scanner scanner;

    public StudentTracker(Scanner scanner) {
        this.students = new HashSet<>();
        this.departmentMap = new HashMap<>();
        this.scanner = scanner;
    }

    public void addStudent() throws InvalidStudentException {
        StudentUtility.displayStudentTypes();
        int choice = Helpers.validateIntRange(scanner, 1, 2);

        Student student;
        switch (choice) {
            case 1:
                student = createUndergraduate();
                break;
            case 2:
                student = createPostgraduate();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (students.contains(student)) {
            throw new InvalidStudentException("Student already exists with same name and department.");
        }

        students.add(student);

        // Group by department
        String deptKey = student.getDepartment();
        if (!departmentMap.containsKey(deptKey)) {
            departmentMap.put(deptKey, new ArrayList<Student>());
        }
        departmentMap.get(deptKey).add(student);

        System.out.println("Student added successfully! ID: " + student.getId());
    }

    private Student createUndergraduate() throws InvalidStudentException {
        System.out.println("Enter Student Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        Department dept = StudentUtility.printAndGetDepartment(scanner);

        System.out.println("Enter Enrollment Year:");
        int year = Helpers.validateIntRange(scanner, 2000, 2026);

        return new UndergraduateStudent(name, dept, year);
    }

    private Student createPostgraduate() throws InvalidStudentException {
        System.out.println("Enter Student Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        Department dept = StudentUtility.printAndGetDepartment(scanner);

        System.out.println("Enter Research Topic:");
        String topic = Helpers.validateStringNonEmpty(scanner);

        return new PostgraduateStudent(name, dept, topic);
    }

    public void addMarks(String studentId) throws InvalidStudentException {
        Student student = findById(studentId);

        System.out.println("Enter Subject Name:");
        String subject = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Marks (0 - 100):");
        int marks = Helpers.validateIntRange(scanner, 0, 100);

        student.addMarks(subject, marks);
    }

    public void viewStudentById(String studentId) throws InvalidStudentException {
        findById(studentId).printDetails();
    }

    public void viewAllStudents() throws InvalidStudentException {
        if (students.isEmpty()) {
            throw new InvalidStudentException("No students found.");
        }

        ArrayList<Student> sorted = new ArrayList<>(students);
        Collections.sort(sorted); // natural order: by name

        int count = 0;
        for (Student s : sorted) {
            System.out.println("\nStudent " + (++count) + ":");
            s.printDetails();
            System.out.println("============================");
        }
    }

    public void viewRankings() throws InvalidStudentException {
        if (students.isEmpty()) {
            throw new InvalidStudentException("No students found.");
        }

        ArrayList<Student> ranked = new ArrayList<>(students);
        Collections.sort(ranked, new TotalMarksComparator()); // custom: by total marks desc

        System.out.println("\n===== Student Rankings =====");
        int rank = 0;
        for (Student s : ranked) {
            System.out.println("Rank " + (++rank) + " | " + s.getName()
                + " | Total: " + s.getTotalMarks()
                + " | Avg: " + String.format("%.2f", s.getAverageMarks()));
        }
    }

    public void viewByDepartment(String deptName) throws InvalidStudentException {
        List<Student> deptStudents = departmentMap.get(deptName.toUpperCase());

        if (deptStudents == null || deptStudents.isEmpty()) {
            throw new InvalidStudentException("No students found in department: " + deptName);
        }

        System.out.println("Students in department: " + deptName);
        int count = 0;
        for (Student s : deptStudents) {
            System.out.println("\nStudent " + (++count) + ":");
            s.printDetails();
            System.out.println("----------------------------");
        }
    }

    // Iterator-based safe removal of students with avg marks < 40
    public void removeIneligibleStudents() {
        Iterator<Student> iterator = students.iterator();
        int removedCount = 0;

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageMarks() < 40 && !student.getSubjectMarks().isEmpty()) {

                // Remove from department map too
                List<Student> deptList = departmentMap.get(student.getDepartment());
                if (deptList != null) {
                    deptList.remove(student);
                }

                iterator.remove();
                System.out.println("Removed: " + student.getName() + " (Avg: "
                    + String.format("%.2f", student.getAverageMarks()) + ")");
                removedCount++;
            }
        }

        if (removedCount == 0) {
            System.out.println("No ineligible students found.");
        } else {
            System.out.println(removedCount + " student(s) removed.");
        }
    }

    // Private helper — reused across methods
    private Student findById(String studentId) throws InvalidStudentException {
        for (Student s : students) {
            if (s.getId().equals(studentId)) {
                return s;
            }
        }
        throw new InvalidStudentException("Student not found with ID: " + studentId);
    }
}