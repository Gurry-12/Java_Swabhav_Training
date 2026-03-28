package com.gurpreet.sorted;

import java.util.Comparator;
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

public class SortStudentByMarks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> students = List.of(new Student("Aarav Sharma", 85), new Student("Priya Singh", 45),
				new Student("Rohan Kapoor", 72), new Student("Ananya Verma", 58), new Student("Vikram Patel", 91),
				new Student("Sneha Reddy", 62), new Student("Karan Malhotra", 39), new Student("Meera Iyer", 78),
				new Student("Arjun Rao", 55), new Student("Neha Gupta", 67));
		
		List<Student> sortedStudents = students.stream().sorted(Comparator.comparingInt(Student::getMarks)).toList();
		
		sortedStudents.forEach(System.out::println);
	}

}
