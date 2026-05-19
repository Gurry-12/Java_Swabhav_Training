package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.CoursesDAO;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
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

		if (!checkLogin(request, response)) {
			return;
		}

		String idStr = request.getParameter("id");
		if (idStr == null || idStr.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/courses");
			return;
		}

		int courseId = Integer.parseInt(idStr);

		try (Connection connection = DBConnection.getConnection()) {

			int registrationCount = coursesDAO.getRegistrationCountByCourse(connection, courseId, "Active");

			if (registrationCount > 0) {
				// Cannot delete - course has registrations
				request.setAttribute("error",
						"Cannot delete! Course has " + registrationCount + " active registration(s).");

				// Forward to course list to show error
				RequestDispatcher rd = request.getRequestDispatcher( "/courses");
				rd.forward(request, response);

			} else {
				// Safe to delete
				coursesDAO.deleteCourse(connection, courseId);
				response.sendRedirect(request.getContextPath() + "/courses");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error occurred while deleting course.");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
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
}