package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.util.DBConnection;

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

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("login");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		try (Connection connection = DBConnection.getConnection()) {
			registrationDAO.deleteRegistration(connection, id);
			response.sendRedirect(request.getContextPath() + "/registrations");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/registrations");
		}
	}
}