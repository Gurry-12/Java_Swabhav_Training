package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.studentcourse.dao.CoursesDAO;
import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/courses")
public class ViewCoursesServlet extends HttpServlet {

	private CoursesDAO coursesDAO;

	@Override
	public void init() throws ServletException {
		this.coursesDAO = new CoursesDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("login");
			return;
		}

		try (Connection connection = DBConnection.getConnection()) {

			List<Course> courses = coursesDAO.getAllCourses(connection);
			request.setAttribute("courses", courses);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/course-list.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "DB Connection Issue");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/course-list.jsp");
			rd.forward(request, response);
		}
	}
}