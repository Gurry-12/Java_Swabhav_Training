package com.project.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.project.app.dao.CourseDAO;
import com.project.app.dao.DepartmentDAO;
import com.project.app.dao.RegistrationDAO;
import com.project.app.dao.StudentDAO;
import com.project.app.exception.DuplicateRegistrationException;
import com.project.app.exception.DuplicateStudentFoundException;
import com.project.app.exception.RegistrationNotFoundException;
import com.project.app.exception.StudentEnrollmentException;
import com.project.app.exception.StudentNotFoundException;
import com.project.app.model.Registration;
import com.project.app.model.Student;
import com.project.app.util.DBUtil;

public class StudentService {

	private StudentDAO studentDAO;
	private RegistrationDAO registrationDAO;
	private CourseDAO courseDAO;
	private DepartmentDAO departmentDAO;

	public StudentService() {
		this.studentDAO = new StudentDAO();
		this.registrationDAO = new RegistrationDAO();
		this.courseDAO = new CourseDAO();
		this.departmentDAO = new DepartmentDAO();
	}

	public void addStudent(int id, String name, int age, int departmentId) {

		try (Connection connection = DBUtil.getConnection()) {

			if (studentDAO.existsById(connection, id)) {
				throw new DuplicateStudentFoundException("Student with ID " + id + " exists.");
			}
			Student student = new Student(id, name, age, departmentId);
			int result = studentDAO.insertStudent(connection, student);

			if (result != 0) {
				System.out.println("Student added successfully");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void registerStudentCourse(int studentId, int courseId, double feePaid) {

		try (Connection connection = DBUtil.getConnection()) {

			try {
				connection.setAutoCommit(false);

				if (!studentDAO.existsById(connection, studentId)) {
					throw new StudentNotFoundException("Student with ID " + studentId + " not exists.");
				}

				if (registrationDAO.isAlreadyRegistered(connection, studentId, courseId)) {
					throw new DuplicateRegistrationException("Duplicate registration of Course ' " + courseId);
				}

				Registration registration = new Registration(studentId, courseId, feePaid);

				int result = registrationDAO.insertRegistration(connection, registration);

				if (result != 0) {
					System.out.println("Course registration added successfully");
				}

				connection.commit();
			} catch (SQLException e1) {
				connection.rollback();
				throw e1;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// student + course + with id
	public void getStudentById(int id) {

		try (Connection connection = DBUtil.getConnection()) {

			List<String[]> resultStudent = registrationDAO.selectByStudentId(connection, id);

			if (resultStudent == null || resultStudent.isEmpty()) {
				throw new StudentNotFoundException("No record found. ");
			}

			printStudentTable(resultStudent);

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

//	getAllStudentsWithCourses()
	public void getAllStudentsWithCourses() {

		try (Connection connection = DBUtil.getConnection()) {

			List<String[]> records = studentDAO.selectAllWithRegistrations(connection);
			if (records.isEmpty()) {
				throw new StudentNotFoundException("No record found. ");
			}

			printStudentTable(records);

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

//	updateStudent()
	public void updateStudent(int studentId, String name, int departmentId) {

		try (Connection connection = DBUtil.getConnection()) {

			// check student exist
			if (!studentDAO.existsById(connection, studentId)) {
				throw new StudentNotFoundException("Studnet not found at id " + studentId);
			}
			int result = studentDAO.updateStudent(connection, studentId, name, departmentId);

			if (result > 0) {
				System.out.println("Student update Successfully.");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

//	updateCourseFee()
	public void updateCourseFee(int studentId, int courseId, double newFee) {

		try (Connection connection = DBUtil.getConnection()) {

			// check student exist
			if (!studentDAO.existsById(connection, studentId)) {
				throw new StudentNotFoundException("Student not found at id " + studentId);
			}

			if (!registrationDAO.isAlreadyRegistered(connection, studentId, courseId)) {
				throw new RegistrationNotFoundException("Course registration for student not exist");
			}
			int result = registrationDAO.updateCourseFee(connection, studentId, courseId, newFee);

			if (result > 0) {
				System.out.println("Registration update Successfully.");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

//	cancelRegistration()
	public void cancelRegistration(int studentId, int courseId) {

		try (Connection connection = DBUtil.getConnection()) {

			// check registration exist or not
			if (!registrationDAO.isAlreadyRegistered(connection, studentId, courseId)) {
				throw new RegistrationNotFoundException("Course registration for student not exist");
			}

			int result = registrationDAO.deleteRegistration(connection, studentId, courseId);
			if (result > 0) {
				System.out.println("Registration cancel sucessfully");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

//	deleteStudent()
	public void deleteStudent(int studentId) {

		try (Connection connection = DBUtil.getConnection()) {

			try {
				connection.setAutoCommit(false);

				if (!studentDAO.existsById(connection, studentId)) {
					throw new StudentNotFoundException("Studnet not found at id " + studentId);
				}

				int result = registrationDAO.deleteAllRegistrationByStudentId(connection, studentId);
				System.out.println(result + " registration cancel fro the student ");

				int row = studentDAO.deleteStudent(connection, studentId);
				if (row > 0) {
					System.out.println("Student cancel sucessfully");
				}

				connection.commit();
			} catch (SQLException e1) {
				connection.rollback();
				throw e1;
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

//	getHighPayingStudents()
	public void getHighPayingStudents(double minFee) {

		try (Connection connection = DBUtil.getConnection()) {

			List<String[]> list = registrationDAO.getHighPayingStudents(connection, minFee);

			if (list.isEmpty()) {
				System.out.println("No records found");
				return;
			}

			System.out.printf("%n  %-6s %-15s %-5s %-15s %-8s %-12s%n", "ID", "Name", "Age", "Department", "Courses",
					"Total Fees");
			System.out.println("  " + "-".repeat(72));
			for (String[] r : list) {
				System.out.printf("  %-6s %-15s %-5s %-15s %-8s %-12s%n", r[0], r[1], r[2], r[3], r[4], r[5]);
			}
			System.out.println("  " + "-".repeat(72));

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

//	getCourseWiseCount()

	public void getCourseWiseCount() {

		try (Connection connection = DBUtil.getConnection()) {

			List<String[]> list = registrationDAO.getCourseWiseCount(connection);

			if (list.isEmpty()) {
				System.out.println("No records found");
				return;
			}

			System.out.printf("%n  %-15s %-15s%n", "Course Name", "Student Count");
			System.out.println("  " + "-".repeat(47));
			for (String[] r : list) {
				System.out.printf("  %-15s %-15s%n", r[0], r[1]);
			}
			System.out.println("  " + "-".repeat(47));

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// student id valid
	public boolean checkStudentExist(int id) {
		try (Connection connection = DBUtil.getConnection()) {

			return studentDAO.existsById(connection, id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> listAllCourses() {
		try (Connection connection = DBUtil.getConnection()) {

			List<String> allCourses = courseDAO.getAllCourses(connection);
			if (allCourses == null || allCourses.isEmpty()) {
				throw new StudentEnrollmentException("Course not found");
			}
			return allCourses;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> listAllDepartments() {
		try (Connection connection = DBUtil.getConnection()) {

			List<String> allDepartments = departmentDAO.getAllDepartments(connection);
			if (allDepartments == null || allDepartments.isEmpty()) {
				throw new StudentEnrollmentException("Course not found");
			}
			return allDepartments;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addNewCourses(String course) {
		try (Connection connection = DBUtil.getConnection()) {

			int result = courseDAO.addNewCourses(connection, course);
			if (result != 0) {
				System.out.println("Course added successfully");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void addNewDepartment(String department) {
		try (Connection connection = DBUtil.getConnection()) {

			int result = departmentDAO.addNewDepartment(connection, department);
			if (result != 0) {
				System.out.println("Department added successfully");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public List<String> listStudentAllCourses(int studentId) {
		try (Connection connection = DBUtil.getConnection()) {

			List<String> allCourses = courseDAO.getStudentAllCourses(connection, studentId);
			if (allCourses == null || allCourses.isEmpty()) {
				throw new StudentEnrollmentException("Course not found");
			}
			return allCourses;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void printStudentTable(List<String[]> rows) {
		System.out.printf("%n  %-6s %-15s %-5s %-15s %-20s %-12s%n", "ID", "Name", "Age", "Department", "Course",
				"Fees Paid");
		System.out.println("  " + "-".repeat(82));
		for (String[] r : rows) {
			System.out.printf("  %-6s %-15s %-5s %-15s %-20s %-12s%n", r[0], r[1], r[2], r[3], r[4], r[5]);
		}
		System.out.println("  " + "-".repeat(82));
	}

}
