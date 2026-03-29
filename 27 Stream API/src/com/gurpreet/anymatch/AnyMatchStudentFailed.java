package com.gurpreet.anymatch;

import java.util.List;

class Student {
	private String name;
	private int marks;

	public Student(String name, int marks) {
		this.name = name;
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public int getMarks() {
		return marks;
	}

	@Override
	public String toString() {
		return name + " - " + marks;
	}
}
public class AnyMatchStudentFailed {
	public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Ravi", 75),
            new Student("Priya", 35),
            new Student("Amit", 82),
            new Student("Neha", 29)
        );

        boolean hasFailed = students.stream().anyMatch(student -> student.getMarks() < 40);

        System.out.println("Any student failed (marks < 40): " + hasFailed);
    }

}
