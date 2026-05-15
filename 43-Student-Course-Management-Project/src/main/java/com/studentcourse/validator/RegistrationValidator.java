package com.studentcourse.validator;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

public class RegistrationValidator {

	public static void validateStudentId(String studentIdStr, String errorAttribute, HttpServletRequest request) {
		try {
			int id = Integer.parseInt(studentIdStr);
			if (id <= 0) {
				request.setAttribute(errorAttribute, "Please select a valid student.");
			}
		} catch (Exception e) {
			request.setAttribute(errorAttribute, "Please select a student.");
		}
	}

	public static void validateCourseId(String courseIdStr, String errorAttribute, HttpServletRequest request) {
		try {
			int id = Integer.parseInt(courseIdStr);
			if (id <= 0) {
				request.setAttribute(errorAttribute, "Please select a valid course.");
			}
		} catch (Exception e) {
			request.setAttribute(errorAttribute, "Please select a course.");
		}
	}

	public static void validateRegistrationDate(String dateStr, String errorAttribute, HttpServletRequest request) {
		if (dateStr == null || dateStr.trim().isEmpty()) {
			request.setAttribute(errorAttribute, "Registration date is required.");
		} else {
			try {
				LocalDate date = LocalDate.parse(dateStr);
				if (date.isAfter(LocalDate.now())) {
					request.setAttribute(errorAttribute, "Registration date cannot be in the future.");
				}
			} catch (Exception e) {
				request.setAttribute(errorAttribute, "Please enter a valid registration date.");
			}
		}
	}

	public static void validateStatus(String status, String errorAttribute, HttpServletRequest request) {
		if (status == null
				|| (!status.equals("Active") && !status.equals("Completed") && !status.equals("Cancelled"))) {
			request.setAttribute(errorAttribute, "Please select a valid status.");
		}
	}
}
