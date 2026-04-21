package com.gurpreet.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gurpreet.model.GradingSystem;

class GradingSystemTest {

	GradingSystem grade = new GradingSystem();
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 55, 99, 100})
	void shouldReturnTrueWhenMarksIsValid(int a) {
		assertTrue(grade.isValidMarks(a));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-1, -2, 101, 200})
	void shouldReturnFalseWhenMarksIsInvalid(int a) {
		assertFalse(grade.isValidMarks(a));
	}
}
