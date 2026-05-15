package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.CoursesDAO;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/course/delete")
public class DeleteCourseServlet extends HttpServlet {

	private CoursesDAO coursesDAO; 
	
	@Override
	public void init() throws ServletException {
		 coursesDAO = new CoursesDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("login");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		try (Connection connection = DBConnection.getConnection()) {

			int count = coursesDAO.getRegistrationCountByCourse(connection, id);

			if (count > 0) {
				request.setAttribute("error", "Cannot delete! Course has active registrations.");
				response.sendRedirect(request.getContextPath() + "/courses");
			} else {
				coursesDAO.deleteCourse(connection, id);
				response.sendRedirect(request.getContextPath() + "/courses");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("courses");
		}
	}
}