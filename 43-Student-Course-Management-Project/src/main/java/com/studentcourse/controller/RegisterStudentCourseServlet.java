package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.studentcourse.dao.CoursesDAO;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentsDAO;
import com.studentcourse.model.Course;
import com.studentcourse.model.Registration;
import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;
import com.studentcourse.validator.RegistrationValidator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registration/add")
public class RegisterStudentCourseServlet extends HttpServlet {

	private RegistrationDAO registrationDAO;
	private StudentsDAO studentsDAO;
	private CoursesDAO coursesDAO;

	@Override
	public void init() throws ServletException {
		registrationDAO = new RegistrationDAO();
		studentsDAO = new StudentsDAO();
		coursesDAO = new CoursesDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		try (Connection connection = DBConnection.getConnection()) {
			List<Student> students = studentsDAO.getAllStudents(connection);
			List<Course> courses = coursesDAO.getAllCourses(connection);

			request.setAttribute("students", students);
			request.setAttribute("courses", courses);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("dashboard");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		// Get parameters
		String studentIdStr = request.getParameter("studentId");
		String courseIdStr = request.getParameter("courseId");
		String registrationDateStr = request.getParameter("registrationDate");
		String status = request.getParameter("status");

		int studentId = 0;
		int courseId = 0;
		LocalDate registrationDate = null;

		// === Server-side Validation ===
		RegistrationValidator.validateStudentId(studentIdStr, "studentIdError",  request);
		RegistrationValidator.validateCourseId(courseIdStr, "courseIdError", request);
		RegistrationValidator.validateRegistrationDate(registrationDateStr, "registrationDateError", request);
		RegistrationValidator.validateStatus(status, "statusError", request);

		// check validation errors 
		if (hasFieldErrors(request)) {
			request.setAttribute("selectedStudentId", studentIdStr);
			request.setAttribute("selectedCourseId", courseIdStr);
			request.setAttribute("selectedDate", registrationDateStr);
			request.setAttribute("selectedStatus", status);

			doGet(request, response);
			return;
		}

		// Parse values after validation
		studentId = Integer.parseInt(studentIdStr);
		courseId = Integer.parseInt(courseIdStr);
		registrationDate = LocalDate.parse(registrationDateStr);

		// Check for duplicate registration
		boolean alreadyExists = false;
		try (Connection connection = DBConnection.getConnection()) {
			alreadyExists = registrationDAO.checkExistRegitration(connection, studentId, courseId);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error while checking registration.");
			doGet(request, response);
			return;
		}

		if (alreadyExists) {
			request.setAttribute("error", "Student is already registered for this course.");
			doGet(request, response);
			return;
		}

		Registration reg = new Registration(studentId, courseId, registrationDate, status);

		try (Connection connection = DBConnection.getConnection()) {
			registrationDAO.addRegistration(connection, reg);
			response.sendRedirect(request.getContextPath() + "/registrations");
		} catch (SQLException e) {
			request.setAttribute("error", "Failed to register! Please try again.");
			doGet(request, response);
		}
	}

	private boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;
	}

	private boolean hasFieldErrors(HttpServletRequest request) {
		return request.getAttribute("studentIdError") != null || request.getAttribute("courseIdError") != null
				|| request.getAttribute("registrationDateError") != null || request.getAttribute("statusError") != null;
	}
}