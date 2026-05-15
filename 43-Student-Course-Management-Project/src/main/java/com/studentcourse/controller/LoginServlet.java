package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.AdminDAO;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login-action")
public class LoginServlet extends HttpServlet {

	private AdminDAO adminDAO;

	@Override
	public void init() throws ServletException {
		this.adminDAO = new AdminDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean rememberMe = request.getParameter("rememberMe") != null;

		try (Connection connection = DBConnection.getConnection()) {

			if (adminDAO.varifyAdmin(connection, username, password)) {

				HttpSession session = request.getSession();
				session.setAttribute("loggedInUser", username);

				if (rememberMe) {
					Cookie cookie = new Cookie("username", username);
					cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("username", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}

				response.sendRedirect("dashboard");

			} else {
				request.setAttribute("error", "Invalid username or password!");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "DB Connection Issue");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(request, response);
		}
	}
}