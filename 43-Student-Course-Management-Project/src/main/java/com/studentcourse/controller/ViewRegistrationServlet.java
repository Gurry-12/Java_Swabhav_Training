package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.model.RegistrationDetails;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registrations")
public class ViewRegistrationServlet extends HttpServlet {

	private RegistrationDAO registrationDAO;

	@Override
	public void init() throws ServletException {
		this.registrationDAO = new RegistrationDAO();
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

			List<RegistrationDetails> registrations = registrationDAO.getAllRegistrations(connection);
			request.setAttribute("registrations", registrations);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration-list.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "DB Connection Issue");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration-list.jsp");
			rd.forward(request, response);
		}
	}
}