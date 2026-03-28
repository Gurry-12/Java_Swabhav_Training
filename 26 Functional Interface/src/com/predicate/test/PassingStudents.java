package com.predicate.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.predicate.models.Student;

public class PassingStudents {

	public static void main(String[] args) {
		
		List<Student> students = Arrays.asList(
                new Student("Amit", 75),
                new Student("Priya", 38),
                new Student("Rahul", 42),
                new Student("Sneha", 29),
                new Student("Vikas", 55)
        );
		
		Predicate<Student> isPassing = student -> student.getMarks() >= 40;
		
		for(Student student : students) {
			if(isPassing.test(student)) {
				System.out.println(student);
			}
		}

	}

}
