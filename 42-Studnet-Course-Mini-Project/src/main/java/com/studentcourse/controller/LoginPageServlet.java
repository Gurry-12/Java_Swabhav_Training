package com.studentcourse.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String rememberUsername = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName())) {
					rememberUsername = cookie.getName();
					break;
				}
			}
		}
		
		request.setAttribute("username", rememberUsername);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/views/login.jsp");
		requestDispatcher.forward(request, response);

	}
}