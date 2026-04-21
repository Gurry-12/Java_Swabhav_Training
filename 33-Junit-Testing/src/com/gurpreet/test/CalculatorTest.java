package com.gurpreet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.gurpreet.model.Calculator;

/*
 *  Assignment 1
 */
class CalculatorTest {
	Calculator calculator = new Calculator();

	@ParameterizedTest
    @CsvSource({
    	"7 , 3 , 10",
    	"-7, -3, -10",
    	"-5 , 5 , 0",
    	"4 , -6, -2",
    	"0 , 5, 5",
    	"6 , 0 , 6"    	
    })
	 @DisplayName("Testing Addition with Edge Cases")
    void testAdditionOnMultipleInputs(int a , int b, int expected) {
    	assertEquals(expected , calculator.add(a, b));
    }
	
	@ParameterizedTest
	@CsvSource({
    	"7 , 3 , 4",
    	"-7, -3, -4",
    	"-5 , 5 , -10",
    	"4 , -6, 10",
    	"0 , 5, -5",
    	"6 , 0 , 6"    	
    })
	@DisplayName("Testing Subtraction with Edge Cases")
	void testSubtractionOnMultipleInputs(int a , int b, int expected) {
    	assertEquals(expected , calculator.subtract(a, b));
    }
	
	@ParameterizedTest
	@CsvSource({
    	"7 , 3 , 21",
    	"-7, -3, 21",
    	"-5 , 5 , -25",
    	"4 , -6, -24",
    	"0 , 5, 0",
    	"6 , 0 , 0"    	
    })
	@DisplayName("Testing Multiplication with Edge Cases")
	void testMultiplicationOnMultipleInputs(int a , int b, int expected) {
    	assertEquals(expected , calculator.multiply(a, b));
    }
	
	@ParameterizedTest
	@CsvSource({
    	"10, 5, 2.0",
    	"-5, -5, 1.0",
    	"0 , 5 , 0.0",
    	"5, -2, -2.5"   	
    })
	@DisplayName("Testing Division with Edge Cases")
	void testDivisionOnMultipleInputs(int a , int b,double expected) {
    	assertEquals(expected , calculator.divide(a, b));
    }
	
	@ParameterizedTest
	@ValueSource(ints = {10, -5, 0})
	void testDivideByZeroThrowsException(int a) {
		assertThrows(ArithmeticException.class,  () -> calculator.divide(a,0));
	}
	
	@Test
	@Disabled
	void testAdd() {
		assertEquals(10, calculator.add(7, 3));
		assertEquals(-10, calculator.add(-7, -3));
		assertEquals(0, calculator.add(-5, 5));
		assertEquals(5, calculator.add(5, 0));
		assertNotEquals(0, calculator.add(1, 1));
	}

	@Test
	@Disabled
	void testSubtract() {
		assertEquals(5, calculator.subtract(10, 5));
		assertEquals(-4, calculator.subtract(-7, -3));
		assertEquals(0, calculator.subtract(5, 5));
		assertEquals(-5, calculator.subtract(0, 5));
		assertNotEquals(10, calculator.subtract(5, 5));
	}

	@Test
	@Disabled
	void testMultiply() {
		assertEquals(25, calculator.multiply(5, 5));
		assertEquals(25, calculator.multiply(-5, -5));
		assertEquals(-25, calculator.multiply(5, -5));
		assertEquals(0, calculator.multiply(5, 0));
		assertNotEquals(1, calculator.multiply(5, 0));
	}

	@Test
	@Disabled
	void testDivide() {
		assertEquals(2.0, calculator.divide(10, 5));
		assertEquals(1.0, calculator.divide(-5, -5));
		assertEquals(0.0, calculator.divide(0, 5));
		assertEquals(-2.5, calculator.divide(5, -2));
		assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
	}
}