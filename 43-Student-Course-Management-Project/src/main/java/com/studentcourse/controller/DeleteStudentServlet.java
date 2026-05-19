package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.StudentsDAO;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {

	private StudentsDAO studentsDAO;

	@Override
	public void init() throws ServletException {
		studentsDAO = new StudentsDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response)) {
			return;
		}

		String idStr = request.getParameter("id");
		if (idStr == null || idStr.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/students");
			return;
		}

		int studentId = Integer.parseInt(idStr);

		try (Connection connection = DBConnection.getConnection()) {

			int registrationCount = studentsDAO.getRegistrationCountByStudent(connection, studentId);

			if (registrationCount > 0) {
				// Cannot delete - has active registrations
				request.setAttribute("error",
						"Cannot delete! Student is registered in " + registrationCount + " course(s).");

				// Forward to student list so error message can be shown
				RequestDispatcher rd = request.getRequestDispatcher("/students");
				rd.forward(request, response);

			} else {
				// Safe to delete
				studentsDAO.deleteStudent(connection, studentId);
				response.sendRedirect(request.getContextPath() + "/students");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error occurred while deleting student.");

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