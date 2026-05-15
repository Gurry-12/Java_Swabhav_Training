package com.studentcourse.validator;

import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;

public class StudentValidator {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
	private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");
	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

	// Validation Helper Methods

	public static void validateNameField(String value, String fieldName, int minLen, int maxLen, String errorAttribute,
			HttpServletRequest request) {

		if (value == null || value.trim().isEmpty()) {
			request.setAttribute(errorAttribute, fieldName + " is required.");
		} else if (!NAME_PATTERN.matcher(value).matches()) {
			request.setAttribute(errorAttribute, fieldName + " should contain only letters and spaces.");
		} else if (value.trim().length() < minLen) {
			request.setAttribute(errorAttribute, fieldName + " must be at least " + minLen + " characters.");
		} else if (value.trim().length() > maxLen) {
			request.setAttribute(errorAttribute, fieldName + " cannot exceed " + maxLen + " characters.");
		}
	}

	public static void validateAge(String ageStr, HttpServletRequest request) {
		if (ageStr == null || ageStr.trim().isEmpty()) {
			request.setAttribute("ageError", "Age is required.");
			return;
		}
		try {
			int age = Integer.parseInt(ageStr);
			if (age < 18 || age > 100) {
				request.setAttribute("ageError", "Age must be between 18 and 100.");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("ageError", "Please enter a valid age.");
		}
	}

	public static void validateEmail(String email, String errorAttribute, HttpServletRequest request) {
		if (email == null || email.trim().isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
			request.setAttribute(errorAttribute, "Please enter a valid email address.");
		}

	}

	public static void validatePhone(String phone, String errorAttribute, HttpServletRequest request) {
		if (phone == null || phone.trim().isEmpty() || !PHONE_PATTERN.matcher(phone).matches()) {
			request.setAttribute(errorAttribute, "Phone number must be exactly 10 digits.");
		}
	}

}
