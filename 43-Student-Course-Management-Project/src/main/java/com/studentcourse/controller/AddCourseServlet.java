package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.CoursesDAO;
import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;
import com.studentcourse.validator.CourseValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/course/add")
public class AddCourseServlet extends HttpServlet {

	private CoursesDAO coursesDAO;

	@Override
	public void init() throws ServletException {
		coursesDAO = new CoursesDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!checkLogin(request, response))
			return;

		// Get parameters safely
		String courseName = request.getParameter("courseName");
		String duration = request.getParameter("duration");
		String feesStr = request.getParameter("fees");
		String trainerName = request.getParameter("trainerName");

		// === Server-side Validation ===
		CourseValidator.validateCourseNameField(courseName, 1, 100, "courseNameError", request);
		CourseValidator.validateDuration(duration, "durationError", request);
		CourseValidator.validateTrainerNameField(trainerName, 3, 80, "trainerNameError", request);

		// Fees Validation
		double fees = CourseValidator.validateFee(feesStr, "feesError", request);

		// check errors
		if (hasErrors(request)) {
			// Repopulate form fields
			request.setAttribute("courseName", courseName);
			request.setAttribute("duration", duration);
			request.setAttribute("fees", feesStr);
			request.setAttribute("trainerName", trainerName);

			request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
			return;
		}

		// All validations passed
		Course course = new Course(courseName, duration, fees, trainerName);

		try (Connection connection = DBConnection.getConnection()) {
			coursesDAO.addCourse(connection, course);
			response.sendRedirect(request.getContextPath() + "/courses");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to add course! Please try again.");
			request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
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