package com.exam.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculateResult")
public class ResultServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		// Extracting data with matching names
		String name = request.getParameter("studentName");
		String roll = request.getParameter("rollNumber");
		String m1 = request.getParameter("marks1");
		String m2 = request.getParameter("marks2");
		String m3 = request.getParameter("marks3");

		// 1. Validation Logic
		if (name.isEmpty() || roll.isEmpty() || m1.isEmpty() || m2.isEmpty() || m3.isEmpty()) {
			handleError(request, response, "All fields must be filled.");
			return;
		}

		try {
			int s1 = Integer.parseInt(m1);
			int s2 = Integer.parseInt(m2);
			int s3 = Integer.parseInt(m3);

			if (s1 < 0 || s1 > 100 || s2 < 0 || s2 > 100 || s3 < 0 || s3 > 100) {
				handleError(request, response, "Marks must be between 0 and 100.");
				return;
			}

			// 2. Calculation Logic
			int total = s1 + s2 + s3;
			double percentage = total / 3.0;
			String status;

			// Result Status Rules
			if (percentage >= 75)
				status = "Distinction";
			else if (percentage >= 60)
				status = "First Class";
			else if (percentage >= 40)
				status = "Pass";
			else
				status = "Fail";

			// 3. Passing Data via Attributes
			request.setAttribute("studentName", name);
			request.setAttribute("rollNumber", roll);
			request.setAttribute("total", total);
			request.setAttribute("percentage", String.format("%.2f", percentage));
			request.setAttribute("status", status);

			// Forward to result page
			request.getRequestDispatcher("resultPreview.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			handleError(request, response, "Please enter valid numeric marks.");
		}
	}

	private void handleError(HttpServletRequest req, HttpServletResponse res, String msg) throws IOException {
		req.getSession().setAttribute("errorMessage", msg);
		res.sendRedirect("examForm.jsp");
	}
}
