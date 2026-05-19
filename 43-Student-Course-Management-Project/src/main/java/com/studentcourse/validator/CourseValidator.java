package com.studentcourse.validator;

import java.util.regex.Pattern;
import jakarta.servlet.http.HttpServletRequest;

public class CourseValidator {

	private static final Pattern COURSE_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s\\+\\#\\.\\-]+$");
	private static final Pattern TRAINER_NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");
	private static final Pattern DURATION_PATTERN = Pattern.compile("^\\d+\\s+[a-zA-Z\\s]+$");

	public static void validateCourseNameField(String value, int minLen, int maxLen, String errorAttribute,
			HttpServletRequest request) {

		if (value == null || value.trim().isEmpty()) {
			request.setAttribute(errorAttribute, "Course Name is required.");
		} else if (!COURSE_NAME_PATTERN.matcher(value).matches()) {
			request.setAttribute(errorAttribute, "Course Name can contain letters, numbers, spaces, +, #, ., -");
		} else if (value.trim().length() < minLen) {
			request.setAttribute(errorAttribute, "Course Name must be at least " + minLen + " characters.");
		} else if (value.trim().length() > maxLen) {
			request.setAttribute(errorAttribute, "Course Name cannot exceed " + maxLen + " characters.");
		}
	}

	public static void validateTrainerNameField(String value, int minLen, int maxLen, String errorAttribute,
			HttpServletRequest request) {

		if (value == null || value.trim().isEmpty()) {
			request.setAttribute(errorAttribute, "Trainer Name is required.");
		} else if (!TRAINER_NAME_PATTERN.matcher(value).matches()) {
			request.setAttribute(errorAttribute, "Trainer Name should contain only letters and spaces.");
		} else if (value.trim().length() < minLen) {
			request.setAttribute(errorAttribute, "Trainer Name must be at least " + minLen + " characters.");
		} else if (value.trim().length() > maxLen) {
			request.setAttribute(errorAttribute, "Trainer Name cannot exceed " + maxLen + " characters.");
		}
	}

	public static void validateDuration(String value, String errorAttribute, HttpServletRequest request) {
		if (value == null || value.trim().isEmpty()) {
			request.setAttribute(errorAttribute, "Duration is required.");
		} else if (!DURATION_PATTERN.matcher(value.trim()).matches()) {
			request.setAttribute(errorAttribute, "Duration should be in format like '6 Months', '12 Weeks', '1 Year'");
		} else if (value.trim().length() < 3) {
			request.setAttribute(errorAttribute, "Duration must be at least 3 characters.");
		} else if (value.trim().length() > 50) {
			request.setAttribute(errorAttribute, "Duration is too long.");
		}
	}

	public static double validateFee(String feesStr, String errorAttribute, HttpServletRequest request) {
		double fees = 0;
		try {
			fees = Double.parseDouble(feesStr != null ? feesStr.trim() : "0");
			if (fees <= 0) {
				request.setAttribute(errorAttribute, "Fees must be greater than 0.");
			}
		} catch (NumberFormatException e) {
			request.setAttribute(errorAttribute, "Please enter a valid fees amount.");
		}
		return fees;
	}
}