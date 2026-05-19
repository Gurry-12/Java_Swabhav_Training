package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.CoursesDAO;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentsDAO;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private StudentsDAO studentsDAO;
	private CoursesDAO coursesDAO;
	private RegistrationDAO registrationDAO;

	@Override
	public void init() throws ServletException {
		this.studentsDAO = new StudentsDAO();
		this.coursesDAO = new CoursesDAO();
		this.registrationDAO = new RegistrationDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(!checkLogin(request, response)) {
			return;
		}
		try (Connection connection = DBConnection.getConnection()) {

			int totalStudents = studentsDAO.countTotalStudents(connection);
			int totalCourses = coursesDAO.countTotalCourses(connection);
			int totalRegistrations = registrationDAO.countTotalRegistrations(connection);

			request.setAttribute("totalStudents", totalStudents);
			request.setAttribute("totalCourses", totalCourses);
			request.setAttribute("totalRegistrations", totalRegistrations);

			request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);

		} catch (SQLException e) {
			request.setAttribute("error", "DB Connection Issue");
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
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
}