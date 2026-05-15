package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.studentcourse.dao.StudentsDAO;
import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;
import com.studentcourse.validator.StudentValidator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/update")
public class UpdateStudentServlet extends HttpServlet {

	private StudentsDAO studentsDAO;

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
	private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");
	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

	@Override
	public void init() throws ServletException {
		studentsDAO = new StudentsDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		String studentIdStr = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String ageStr = request.getParameter("age");
		String city = request.getParameter("city");

		int studentId = Integer.parseInt(studentIdStr);

		// name Validation
		StudentValidator.validateNameField(studentName, "Student Name", 2, 100, "studentNameError", request);

		// city Validation
		StudentValidator.validateNameField(city, "City", 2, 50, "cityError", request);

		// Email Validation
		StudentValidator.validateEmail(email, "emailError", request);

		// Phone Validation
		StudentValidator.validatePhone(phone, "phoneError", request);

		// Age Validation
		StudentValidator.validateAge(ageStr, request);

		if (hasErrors(request)) {
			Student student = new Student(studentId, studentName, email, phone, parseAge(ageStr), city);
			request.setAttribute("student", student);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp");
			rd.forward(request, response);
			return;
		}

		// Validation passed
		int age = Integer.parseInt(ageStr);
		Student student = new Student(studentId, studentName, email, phone, age, city);

		try (Connection connection = DBConnection.getConnection()) {
			studentsDAO.updateStudent(connection, student);
			response.sendRedirect(request.getContextPath() + "/students");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to update student!");
			request.setAttribute("student", student);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp");
			rd.forward(request, response);
		}
	}

	private int parseAge(String ageStr) {
		try {
			return Integer.parseInt(ageStr);
		} catch (Exception e) {
			return 0;
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

	private boolean hasErrors(HttpServletRequest request) {
		return request.getAttribute("studentNameError") != null || request.getAttribute("emailError") != null
				|| request.getAttribute("phoneError") != null || request.getAttribute("ageError") != null
				|| request.getAttribute("cityError") != null;
	}
}