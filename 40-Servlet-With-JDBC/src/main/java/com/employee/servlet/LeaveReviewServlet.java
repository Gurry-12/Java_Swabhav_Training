package com.employee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.employee.dao.EmployeeDAO;
import com.employee.model.LeaveRequest;
import com.employee.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/leaveReview")
public class LeaveReviewServlet extends HttpServlet {

	private EmployeeDAO employeeDAO;

	@Override
	public void init() throws ServletException {
		employeeDAO = new EmployeeDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String employeeId = request.getParameter("employeeId");

		try (Connection connection = DBUtil.getConnection()) {

			LeaveRequest leave = employeeDAO.getLeaveById(connection, employeeId);
			if (leave != null) {
				request.setAttribute("leave", leave);
				request.getRequestDispatcher("leaveReview.jsp").forward(request, response);
			} else {
				response.sendRedirect("index.html");
			}
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Forward to your review page
		request.getRequestDispatcher("leaveReview.jsp").forward(request, response);
	}
}
