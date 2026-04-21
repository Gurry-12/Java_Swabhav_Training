package com.gurpreet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gurpreet.model.Calculator;

class DivisionExceptionTest {

	Calculator calculator = new Calculator();

	@ParameterizedTest
	@ValueSource(ints = { 10, -5, 0 })
	void shouldThrowsExceptionWhenDividingWithZero(int a) {
		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculator.divide(a, 0));

		String expected = "Cannot divide by zero";
		assertEquals(expected, exception.getMessage());
	}
}
