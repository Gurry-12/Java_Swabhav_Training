package com.student.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class CourseRegistrationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("Server initialized.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String studentName = request.getParameter("studentName");
		String email = request.getParameter("email");
		String ageStr = request.getParameter("age");
		String courseName = request.getParameter("courseName");
		String batchTime = request.getParameter("batchTime");

		// 1. Validation Logic
		if (isNullOrEmpty(studentName) || isNullOrEmpty(email) || isNullOrEmpty(courseName)
				|| isNullOrEmpty(batchTime)) {
			// Set error in Session because we are using sendRedirect
			request.getSession().setAttribute("errorMessage", "All fields are required!");
			response.sendRedirect("register.jsp");
			return;
		}

		// 2. Business Rule: Age must be 18 or above
		int age;
		try {
			age = Integer.parseInt(ageStr);
			if (age < 18) {
				request.getSession().setAttribute("errorMessage", "Student must be 18 or older to register.");
				response.sendRedirect("register.jsp");
				return;
			}
		} catch (NumberFormatException e) {
			request.getSession().setAttribute("errorMessage", "Invalid age format.");
			response.sendRedirect("register.jsp");
			return;
		}

		// 3. Success: Use RequestDispatcher and Request Attributes
		request.setAttribute("studentName", studentName);
		request.setAttribute("email", email);
		request.setAttribute("age", age);
		request.setAttribute("courseName", courseName);
		request.setAttribute("batchTime", batchTime);

		RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
		dispatcher.forward(request, response);
	}

	private boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	@Override
	public void destroy() {
		System.out.println("Server Destroyed.");
	}
}
