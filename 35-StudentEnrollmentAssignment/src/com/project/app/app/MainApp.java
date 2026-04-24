package com.project.app.app;

import java.util.Scanner;

import com.project.app.exception.StudentEnrollmentException;
import com.project.app.handler.InputHandler;
import com.project.app.helper.Helpers;
import com.project.app.service.StudentService;

public class MainApp {

	public static void main(String[] args) {

		System.out.println("   Student Course Registration & Fee Management   ");
		Scanner scanner = new Scanner(System.in);
		StudentService service = new StudentService();
		InputHandler handler = new InputHandler(scanner, service);

		boolean isRunning = true;
		while (isRunning) {

			try {
				displayMenu();

				int choice = Helpers.validateIntRange(scanner, 1, 11, "Enter your choice");

				switch (choice) {

				case 1 -> handler.addStudentData();
				case 2 -> handler.addCourseData();
				case 3 -> handler.viewAllStudentsWithCourses();
				case 4 -> handler.searchStudentById();
				case 5 -> handler.updateStudentData();
				case 6 -> handler.updateCourseFeeData();
				case 7 -> handler.cancelRegistrationData();
				case 8 -> handler.deleteStudentData();
				case 9 -> handler.highPayingStudentsReport();
				case 10 -> handler.courseWiseCountReport();
				case 11 -> {
					isRunning = false;
					System.out.println("Thank you for visiting. ");
				}
				default -> System.out.println("Please enter valid choice");
				}
			} catch (StudentEnrollmentException e) {
				System.out.println(" Error: " + e.getMessage());
			} catch (RuntimeException e) {
				System.out.println(" Unexpected error: " + e.getMessage());
			}

		}

	}

	private static void displayMenu() {
		System.out.println();
		System.out.println("""
				1. Add Student
				2. Register for Course
				3. View All Students with Courses
				4. Search Student by ID
				5. Update Student
				6. Update Course Fee
				7. Cancel Registration
				8. Delete Student
				9. High Paying Students Report
				10. Course-wise Student Count
				11. Exit
				""");
		System.out.println();
	}

}
