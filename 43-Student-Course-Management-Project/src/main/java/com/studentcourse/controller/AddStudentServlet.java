package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

@WebServlet("/student/add")
public class AddStudentServlet extends HttpServlet {

	private StudentsDAO studentsDAO;

	@Override
	public void init() throws ServletException {
		studentsDAO = new StudentsDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-form.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		// Get parameters
		String studentName = request.getParameter("studentName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String ageStr = request.getParameter("age");
		String city = request.getParameter("city");

		// === Server-side Validation using Validator ===
		StudentValidator.validateNameField(studentName, "Student Name", 2, 100, "studentNameError", request);
		StudentValidator.validateNameField(city, "City", 2, 50, "cityError", request);
		StudentValidator.validateEmail(email, "emailError", request);
		StudentValidator.validatePhone(phone, "phoneError", request);
		StudentValidator.validateAge(ageStr, request);

		// === Duplicate Check (Email & Phone) ===
		if (!hasErrors(request)) {
			try (Connection connection = DBConnection.getConnection()) {
				if (studentsDAO.varifyDuplicateStudent(connection, email, phone)) {
					request.setAttribute("error", "A student with this email or phone already exists.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Database error occurred while checking duplicates.");
			}
		}

		// If any validation error or duplicate found
		if (hasErrors(request) || request.getAttribute("error") != null) {
			// Repopulate form fields
			request.setAttribute("studentName", studentName);
			request.setAttribute("email", email);
			request.setAttribute("phone", phone);
			request.setAttribute("age", ageStr);
			request.setAttribute("city", city);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-form.jsp");
			rd.forward(request, response);
			return;
		}

		// All checks passed -> Save to database
		int age = Integer.parseInt(ageStr);
		Student student = new Student(studentName, email, phone, age, city);

		try (Connection connection = DBConnection.getConnection()) {
			studentsDAO.addStudent(connection, student);
			response.sendRedirect(request.getContextPath() + "/students");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to add student. Please try again.");
			request.setAttribute("studentName", studentName);
			request.setAttribute("email", email);
			request.setAttribute("phone", phone);
			request.setAttribute("age", ageStr);
			request.setAttribute("city", city);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-form.jsp");
			rd.forward(request, response);
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