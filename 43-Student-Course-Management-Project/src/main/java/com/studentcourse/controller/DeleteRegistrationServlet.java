package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registration/delete")
public class DeleteRegistrationServlet extends HttpServlet {

	private RegistrationDAO registrationDAO;

	@Override
	public void init() throws ServletException {
		registrationDAO = new RegistrationDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response)) {
			return;
		}

		String idStr = request.getParameter("id");
		if (idStr == null || idStr.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/registrations");
			return;
		}

		int registrationId = Integer.parseInt(idStr);

		try (Connection connection = DBConnection.getConnection()) {

			int deleted = registrationDAO.deleteRegistration(connection, registrationId);

			if (deleted > 0) {
				response.sendRedirect(request.getContextPath() + "/registrations");
			} else {
				request.setAttribute("error", "Failed to delete registration. Record may not exist.");
				RequestDispatcher rd = request.getRequestDispatcher("/registrations");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Database error occurred while deleting registration.");

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