package com.employee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.employee.dao.EmployeeDAO;
import com.employee.util.DBUtil;
import com.employee.validator.EmployeeValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/applyLeave")
public class CreateLeaveApplicationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeDAO employeeDao;
	private EmployeeValidator validator;

	@Override
	public void init() throws ServletException {
		this.employeeDao = new EmployeeDAO();
		this.validator = new EmployeeValidator();
		System.out.println("Leave System Initialized.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		// Reading form data
		String employeeName = request.getParameter("employeeName");
		String employeeId = request.getParameter("employeeId");
		String department = request.getParameter("department");
		String leaveType = request.getParameter("leaveType");
		String daysStr = request.getParameter("leaveDays");
		String reason = request.getParameter("reason");

		// 1. Validation Logic
		String error = validator.validate(employeeName, employeeId, department, leaveType, daysStr, reason);
		if (error != null) {
			request.getSession().setAttribute("error", error);
			response.sendRedirect("leaveForm.jsp");
			return;
		}

		// 2. Business Logic
		int days = Integer.parseInt(daysStr);
		String approvalMessage = (days > 5) ? "This leave request requires manager approval"
				: "This leave request can be processed normally";

		// database store
		try (Connection connection = DBUtil.getConnection()) {

			int result = employeeDao.createEmployeeLeaveData(connection, employeeName, employeeId, department,
					leaveType, days, reason, approvalMessage);

			if (result == 0) {
				response.sendRedirect("leaveForm.jsp");
				return;
			}

			request.setAttribute("employeeName", employeeName);
			request.setAttribute("employeeId", employeeId);
			request.getRequestDispatcher("success.jsp").forward(request, response);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect("leaveForm.jsp");
		}

	}

	@Override
	public void destroy() {
		System.out.println("Leave System Shut Down.");
	}
}