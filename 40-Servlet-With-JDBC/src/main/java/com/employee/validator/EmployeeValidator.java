package com.employee.validator;

public class EmployeeValidator {

	public String validate(String name, String id, String dept, String type, String daysStr, String reason) {
		if (isInvalid(name))
			return "Employee Name cannot be empty.";
		if (isInvalid(id))
			return "Employee ID cannot be empty.";
		if (isInvalid(dept))
			return "Department must be provided.";
		if (isInvalid(type))
			return "Please select a Leave Type.";
		if (isInvalid(daysStr))
			return "Number of Leave Days is required.";
		if (isInvalid(reason))
			return "Reason for leave cannot be empty.";

		// 2. Reason Length Validation
		if (reason.trim().length() < 10) {
			return "Reason must contain at least 10 characters.";
		}

		// 3. Numeric and Range Validation
		try {
			int days = Integer.parseInt(daysStr);
			if (days < 1 || days > 10) {
				return "Leave days must be between 1 and 10.";
			}
		} catch (NumberFormatException e) {
			return "Leave days must be a valid number.";
		}

		return null; // All validations passed
	}

	private boolean isInvalid(String s) {
		return s == null || s.trim().isEmpty();
	}
}