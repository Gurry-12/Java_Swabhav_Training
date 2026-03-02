package com.gurpreet.arrayobject.test;

import java.util.Scanner;
import com.gurpreet.arrayobject.models.Course;
import com.gurpreet.arrayobject.models.OnlineCourse;
import com.gurpreet.arrayobject.models.RegularCourse;
import com.gurpreet.helpers.Helpers;

public class CourseTest {

	private static final int MAX_COURSES = 4;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Course[] courses = new Course[MAX_COURSES];
		int courseCount = 0;
		System.out.println("Welcome to the University Course Management System.");

		boolean isRun = true;
		while (isRun) {
			
			displayData();
			
			System.out.println("Enter your choice.");
			int input = Helpers.validateInt(scanner);
			scanner.nextLine();
			
			switch (input) {
			
			case 1:
				if(courseCount > MAX_COURSES) {
					System.out.println("Maximam course limit reached");
					return;
				}
				courseCount = addCourse(scanner, courses, courseCount);
				break;
			case 2:
				displayTotalCourses();
				break;

			case 3:
				displayAllCourses(courses, courseCount);
				break;

			case 4:
				isRun = false;
				System.out.println("Thank you.");
				break;
			
			default:
				System.out.println("Try again, please input valid input 1-4");
			}

		}

		scanner.close();
	}

	private static int addCourse(Scanner scanner, Course[] courses, int courseCount) {
		System.out.println("\nCreating course #" + (courseCount + 1) + " of " + MAX_COURSES);
		System.out.println("What Kind of Course do you want to create.");
		System.out.println("1. : Online Course");
		System.out.println("2. : Regular Course");

		int choice = Helpers.validateInt(scanner);
		scanner.nextLine();
		switch (choice) {
		case 1:
			createOnlineCourse(scanner, courses, courseCount);
			courseCount++;
			break;

		case 2:
			createRegularCourse(scanner, courses, courseCount);
			courseCount++;
			break;

		default:
			System.out.println("Invalid input, please input 1 or 2.");
		}

	return courseCount;
	}

	private static void displayAllCourses(Course[] courses, int count) {
		if (count == 0) {
			System.out.println("No courses available.");
			return;
		}
		System.out.println("\n=== All Courses ===");
		for (int i = 0; i < count; i++) {
			System.out.println("Course Id : " + courses[i].getCourseId());
			System.out.println("Course Name : " + courses[i].getCourseName());
			System.out.println("Course Fee : " + courses[i].getTotalFee());
			System.out.println("Course Type : " + courses[i].getCourseType());
		}
		System.out.println();

	}

	private static void displayTotalCourses() {
		System.out.println("Total courses created: " + Course.getTotalCourses());
	}

	private static void displayData() {
		System.out.println("Menu:");
		System.out.println("1. Create new course");
		System.out.println("2. Display total number of courses created");
		System.out.println("3. Display details of all courses");
		System.out.println("4. Exit");

	}

	private static void createRegularCourse(Scanner scanner, Course[] courses, int index) {
		System.out.print("Enter course name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter base fee: ");
		double baseFee = Helpers.validateDouble(scanner);

		System.out.print("Enter additional charges: ");
		double additional = Helpers.validateDouble(scanner);

		System.out.print("Enter lab charges: ");
		double lab = Helpers.validateDouble(scanner);

		RegularCourse course = new RegularCourse(name, baseFee);
		course.calculateTotalFee(additional, lab);
		courses[index] = course;

		System.out.println("Regular course created successfully.");

	}

	private static void createOnlineCourse(Scanner scanner, Course[] courses, int index) {
		System.out.print("Enter course name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter base fee: ");
		double baseFee = Helpers.validateDouble(scanner);

		System.out.print("Enter technology charges: ");
		double techCharges = Helpers.validateDouble(scanner);

		OnlineCourse course = new OnlineCourse(name, baseFee);
		course.calculateTotalFee(techCharges);
		courses[index] = course;

		System.out.println("Online course created successfully.");

	}

	
}
