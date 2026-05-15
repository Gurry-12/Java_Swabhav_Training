package com.monocept.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class FormServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String fullName = request.getParameter("fullName");
		String age = request.getParameter("age");

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Name: " + fullName + "</h2>");
		out.println("<h2>Age: " + age + "</h2>");
		out.println("</body></html>");

	}
}
