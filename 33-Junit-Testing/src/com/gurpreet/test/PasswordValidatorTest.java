package com.gurpreet.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gurpreet.model.PasswordValidator;

// Assignment 9
class PasswordValidatorTest {

	PasswordValidator validator = new PasswordValidator();

	@Test
	void shouldReturnTrueWhenPasswordIsValid() {
		assertTrue(validator.isValid("StrongPass1"));
	}

	@ParameterizedTest
	@ValueSource(strings = {"Up1", "lowercaseno1", "NoNumbersHere"})
	void shouldReturnFalseWhenPasswordIsInvalid(String s) {
		assertFalse(validator.isValid(s));
	}

	@Test
	void shouldReturnFalseWhenPasswordIsEmptyOrNull() {
		assertAll(
				() -> assertFalse(validator.isValid(null)),
				() -> assertFalse(validator.isValid("")));
	}
}