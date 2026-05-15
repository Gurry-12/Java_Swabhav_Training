package com.monocept.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Forward request to the static HTML page
		request.getRequestDispatcher("/index.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fullName = request.getParameter("fullname");
		String email = request.getParameter("email");

		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();

		response.sendRedirect("dashboard?fullname=" + fullName + "&email=" + email);
//		out.println("<!DOCTYPE html>");
//		out.println("<html><head><title>Success</title>");
//		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
//		out.println("</head><body>");
//		out.println("<div class=\"container\">");
//		out.println("<h2 style=\"color:green;\">Registration Successful</h2>");
//		out.println("<p><strong>Full Name:</strong> " + fullName + "</p>");
//		out.println("<p><strong>Email:</strong> " + email + "</p>");
//		out.println("<br><a href=\"index.html\">← Back to Registration</a>");
//		out.println("</div>");
//		out.println("</body></html>");
	}
}