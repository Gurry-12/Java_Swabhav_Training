package com.function.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StudentGradeGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function<Integer, String> getGrade = marks -> marks >= 75 ? "A" : marks >= 50 ? "B" : "Fail";

		List<Integer> marksList = Arrays.asList(82, 65, 45, 91, 38, 55, 74, 76, 51, 49, 50, 75);

		System.out.println("Student Marks and Grades:\n");
		System.out.println("Marks \t Grade");
		System.out.println("---------------------");

		// Apply the Function to each student's marks
		for (Integer marks : marksList) {
			String grade = getGrade.apply(marks);
			System.out.println(marks + " \t " + grade);
		}
	}

}
