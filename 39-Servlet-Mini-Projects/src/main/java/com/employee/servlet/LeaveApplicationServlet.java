package com.employee.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/applyLeave")
public class LeaveApplicationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("Leave System Initialized.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		// Reading form data
		String empName = request.getParameter("employeeName");
		String empId = request.getParameter("employeeId");
		String dept = request.getParameter("department");
		String leaveType = request.getParameter("leaveType");
		String daysStr = request.getParameter("leaveDays");
		String reason = request.getParameter("reason");

		// 1. Validation Logic
		if (isEmpty(empName) || isEmpty(empId) || isEmpty(dept) || isEmpty(leaveType) || isEmpty(daysStr)
				|| isEmpty(reason)) {

			request.getSession().setAttribute("error", "Invalid input. Ensure all fields are filled.");
			response.sendRedirect("leaveForm.jsp");
			return;
		}

		if (reason.length() < 10) {
			request.getSession().setAttribute("error", "Reason is less thn 10 chars");
			response.sendRedirect("leaveForm.jsp");
			return;
		}

		int days = Integer.parseInt(daysStr);
		if (days < 1 || days > 10) {
			request.getSession().setAttribute("error", "Leave days must be between 1 and 10.");
			response.sendRedirect("leaveForm.jsp");
			return;
		}

		// 2. Business Logic
		String approvalMessage = (days > 5) ? "This leave request requires manager approval"
				: "This leave request can be processed normally";

		// 3. Setting Attributes and Forwarding
		request.setAttribute("employeeName", empName);
		request.setAttribute("employeeId", empId);
		request.setAttribute("department", dept);
		request.setAttribute("leaveType", leaveType);
		request.setAttribute("leaveDays", days);
		request.setAttribute("reason", reason);
		request.setAttribute("approvalMessage", approvalMessage);

		request.getRequestDispatcher("leaveReview.jsp").forward(request, response);
	}

	private boolean isEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	@Override
	public void destroy() {
		System.out.println("Leave System Shut Down.");
	}
}