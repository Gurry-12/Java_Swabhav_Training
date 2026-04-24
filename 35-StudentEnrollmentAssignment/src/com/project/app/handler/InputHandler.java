package com.project.app.handler;

import java.util.List;
import java.util.Scanner;

import com.project.app.exception.DuplicateStudentFoundException;
import com.project.app.exception.StudentNotFoundException;
import com.project.app.helper.Helpers;
import com.project.app.service.StudentService;

public class InputHandler {

	private Scanner scanner;
	private StudentService service;

	public InputHandler(Scanner scanner, StudentService studentService) {
		this.scanner = scanner;
		this.service = studentService;
	}

	public void addStudentData() {
		System.out.println("Enter Student Details : ");

		int id = Helpers.validateIntPositive(scanner, "Enter Id : ");
		if (service.checkStudentExist(id)) {
			throw new DuplicateStudentFoundException("Student Id already exist");
		}
		String name = Helpers.validateString(scanner, "Enter Name : ");
		int age = Helpers.validateIntPositive(scanner, "Enter Age : ");
		int department_id = chooseDepartments();

		service.addStudent(id, name, age, department_id);
	}

	public void addCourseData() {
		System.out.println("Enter Course Details : ");

		int studentId = Helpers.validateIntPositive(scanner, "Enter Student id : ");
		if (!service.checkStudentExist(studentId)) {
			throw new StudentNotFoundException("Student Id not exist");
		}
		int courseId = chooseCourses();
		double feePaid = Helpers.validateDoublePositive(scanner, "Enter fee : ");

		service.registerStudentCourse(studentId, courseId, feePaid);
	}

	public void viewAllStudentsWithCourses() {
		System.out.println("All Students with Courses");
		service.getAllStudentsWithCourses();
	}

	public void searchStudentById() {
		System.out.println("Search Student by ID ");
		int id = Helpers.validateIntPositive(scanner, "Enter Student ID : ");
		if (!service.checkStudentExist(id)) {
			throw new StudentNotFoundException("Student Id not exist");
		}
		service.getStudentById(id);
	}

	public void updateStudentData() {
		System.out.println(" Update Student Details ");
		int id = Helpers.validateIntPositive(scanner, "Enter Student ID : ");

		if (!service.checkStudentExist(id)) {
			throw new StudentNotFoundException("Student Id not exist");
		}
		String newName = Helpers.validateString(scanner, "Enter New Name  : ");
		int newDepartmentId = chooseDepartments();
		service.updateStudent(id, newName, newDepartmentId);
	}

	public void updateCourseFeeData() {
		System.out.println("Update Course Fee ");
		int studentId = Helpers.validateIntPositive(scanner, "Enter Student ID : ");

		if (!service.checkStudentExist(studentId)) {
			throw new StudentNotFoundException("Student Id not exist");
		}
		int courseId = chooseCourses();
		double newFee = Helpers.validateDoublePositive(scanner, "Enter New Fee : ");
		service.updateCourseFee(studentId, courseId, newFee);
	}

	public void cancelRegistrationData() {
		System.out.println("Cancel Course Registration ");
		int studentId = Helpers.validateIntPositive(scanner, "Enter Student ID  : ");
		if (!service.checkStudentExist(studentId)) {
			throw new StudentNotFoundException("Student Id not exist");
		}
		int courseId = chooseCourses();
		service.cancelRegistration(studentId, courseId);
	}

	public void deleteStudentData() {
		System.out.println(" Delete Student ");
		int id = Helpers.validateIntPositive(scanner, " Enter Student ID   : ");
		if (!service.checkStudentExist(id)) {
			throw new StudentNotFoundException("Student Id not exist");
		}
		System.out.print("  Confirm delete student " + id + "? (yes/no): ");
		String confirm = scanner.nextLine().trim();
		if (confirm.equalsIgnoreCase("yes")) {
			service.deleteStudent(id);
		} else {
			System.out.println("  Delete cancelled.");
		}
	}

	public void highPayingStudentsReport() {
		System.out.println("High Paying Students Report ");
		double minFee = Helpers.validateDoublePositive(scanner, "Enter minimum fee  : ");
		service.getHighPayingStudents(minFee);
	}

	public void courseWiseCountReport() {
		System.out.println(" Course-wise Student Count Report ");
		service.getCourseWiseCount();
	}

	public int chooseCourses() {
		List<String> listOfCourses = service.listAllCourses();
		System.out.println("List of courses ");

		for (int i = 0; i < listOfCourses.size(); i++) {
			System.out.println((i + 1) + " " + listOfCourses.get(i));
		}
		return Helpers.validateIntRange(scanner, 1, listOfCourses.size(), "Please choose the course");

	}

	public int chooseDepartments() {
		List<String> listOfDeptartments = service.listAllDepartments();
		System.out.println("List of departments ");

		for (int i = 0; i < listOfDeptartments.size(); i++) {
			System.out.println((i + 1) + " " + listOfDeptartments.get(i));
		}
		return Helpers.validateIntRange(scanner, 1, listOfDeptartments.size(), "Please choose the deprtment");
	}

}
