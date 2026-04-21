package com.gurpreet.model;

public class PasswordValidator {

	public boolean isValid(String password) {
		if (password == null || password.isEmpty())
			return false;

		return password.length() >= 8 && password.chars().anyMatch(Character::isUpperCase)
				&& password.chars().anyMatch(Character::isDigit);
	}
}