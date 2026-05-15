package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {

	private StudentsDAO studentsDAO;

	@Override
	public void init() throws ServletException {
		studentsDAO = new StudentsDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("login");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		try (Connection connection = DBConnection.getConnection()) {

			Student student = studentsDAO.getStudentById(connection, id);
			if (student != null) {
				request.setAttribute("student", student);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/students");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/students");
		}
	}
}