package com.collections.student.abstracts;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.collections.student.enums.Department;
import com.collections.student.exceptions.InvalidStudentException;

public abstract class Student implements Comparable<Student> {

    private String id;
    private String name;
    private Department department;
    private Map<String, Integer> subjectMarks; // subject -> marks

    public Student(String name, Department department, String prefix, long counter)
            throws InvalidStudentException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidStudentException("Student name can't be empty");
        }
        if (department == null) {
            throw new InvalidStudentException("Department can't be null");
        }

        this.id = prefix + counter;
        this.name = name;
        this.department = department;
        this.subjectMarks = new HashMap<>();
    }

    public String getId()             { return id; }
    public String getName()           { return name; }
    public String getDepartment()     { return department.toString(); }
    public Map<String, Integer> getSubjectMarks() { return subjectMarks; }

    public void addMarks(String subject, int marks) throws InvalidStudentException {
        if (subject == null || subject.trim().isEmpty()) {
            throw new InvalidStudentException("Subject name can't be empty");
        }
        if (marks < 0 || marks > 100) {
            throw new InvalidStudentException("Marks must be between 0 and 100");
        }
        subjectMarks.put(subject.trim(), marks);
        System.out.println("Marks added for subject: " + subject);
    }

    public int getTotalMarks() {
        int total = 0;
        for (int marks : subjectMarks.values()) {
            total += marks;
        }
        return total;
    }

    public double getAverageMarks() {
        if (subjectMarks.isEmpty()) return 0.0;
        return (double) getTotalMarks() / subjectMarks.size();
    }

    // Natural ordering: alphabetical by name
    @Override
    public int compareTo(Student other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // Duplicate check: same name + same department
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return other.name.equalsIgnoreCase(name)
            && other.department.equals(department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), department);
    }

    public abstract void printDetails();
}