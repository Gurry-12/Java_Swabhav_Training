package com.gurpreet.findfirst;

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

public class FindFirstPassedStudent {

	public static void main(String[] args) {
		List<Student> students = List.of(new Student("Ravi", 35), new Student("Priya", 82), new Student("Amit", 75),
				new Student("Neha", 29));

		Student firstPassed = students.stream().filter(student -> student.getMarks() >= 40).findFirst().orElse(null);

		System.out.println(firstPassed);
	}
}
