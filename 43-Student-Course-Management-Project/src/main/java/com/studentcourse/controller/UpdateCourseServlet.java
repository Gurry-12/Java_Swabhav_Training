package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.CoursesDAO;
import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;
import com.studentcourse.validator.CourseValidator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/course/update")
public class UpdateCourseServlet extends HttpServlet {

	private CoursesDAO coursesDAO;

	@Override
	public void init() throws ServletException {
		coursesDAO = new CoursesDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		String courseIdStr = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String duration = request.getParameter("duration");
		String feesStr = request.getParameter("fees");
		String trainerName = request.getParameter("trainerName");

		int courseId = Integer.parseInt(courseIdStr);

		// Server-side Validation
		CourseValidator.validateCourseNameField(courseName, 1, 100, "courseNameError", request);
		CourseValidator.validateDuration(duration, "durationError", request);
		CourseValidator.validateTrainerNameField(trainerName, 3, 80, "trainerNameError", request);

		// Fees Validation
		double fees = CourseValidator.validateFee(feesStr, "feesError", request);

		// If validation fails
		if (hasErrors(request)) {
			Course course = new Course(courseId, courseName, duration, fees, trainerName);
			request.setAttribute("course", course);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/course-edit.jsp");
			rd.forward(request, response);
			return;
		}

		Course course = new Course(courseId, courseName, duration, fees, trainerName);

		try (Connection connection = DBConnection.getConnection()) {
			coursesDAO.updateCourse(connection, course);
			response.sendRedirect(request.getContextPath() + "/courses");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to update course!");
			request.setAttribute("course", course);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/course-edit.jsp");
			rd.forward(request, response);
		}
	}

	private boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;
	}

	private boolean hasErrors(HttpServletRequest request) {
		return request.getAttribute("courseNameError") != null || request.getAttribute("durationError") != null
				|| request.getAttribute("feesError") != null || request.getAttribute("trainerNameError") != null;
	}
}