package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.studentcourse.dao.StudentsDAO;
import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/students")
public class ViewStudentsServlet extends HttpServlet {

	private StudentsDAO studentDAO;

	@Override
	public void init() throws ServletException {
		this.studentDAO = new StudentsDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		try (Connection connection = DBConnection.getConnection()) {

			List<Student> students = studentDAO.getAllStudents(connection);
			request.setAttribute("students", students);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-list.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "DB Connection Issue");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			rd.forward(request, response);
		}
	}
}