package com.gurpreet.minmax;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

public class StudentWithHighestMarks {

	public static void main(String[] args) {
		List<Student> students = List.of(new Student("Aarav Sharma", 85), new Student("Priya Singh", 45),
				new Student("Rohan Kapoor", 72), new Student("Ananya Verma", 58), new Student("Vikram Patel", 91),
				new Student("Sneha Reddy", 62), new Student("Karan Malhotra", 39), new Student("Meera Iyer", 78),
				new Student("Arjun Rao", 55), new Student("Neha Gupta", 67));

		Student highestMarksStudent =  (Student) students.stream().max(Comparator.comparingInt(Student::getMarks)).get();
				
		System.out.println(highestMarksStudent);
	}

}
