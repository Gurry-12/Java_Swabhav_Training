package com.employee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.employee.dao.EmployeeDAO;
import com.employee.model.LeaveRequest;
import com.employee.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewLeaves")
public class ViewLeaveApplicationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeDAO employeeDao;

	@Override
	public void init() {
		this.employeeDao = new EmployeeDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBUtil.getConnection()) {
			List<LeaveRequest> leaveList = employeeDao.getAllLeaveRequests(connection);
			request.setAttribute("leaves", leaveList);
			request.getRequestDispatcher("viewLeaves.jsp").forward(request, response);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect("index.html");
		}
	}

}
