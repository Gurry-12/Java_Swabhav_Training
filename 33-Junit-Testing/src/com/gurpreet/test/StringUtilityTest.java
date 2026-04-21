package com.gurpreet.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.gurpreet.model.StringUtility;

/*
 *  Assignment 2
 */

class StringUtilityTest {

	StringUtility util = new StringUtility();

	@ParameterizedTest
	@ValueSource( strings = {" ", "  Hello", "Gurpreet"})
	void shouldReturnFalseWhenInputIsNonEmpty(String s) {
		assertFalse(util.isEmpty(s));
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(util.isEmpty(null));
		assertTrue(util.isEmpty(""));
		assertFalse(util.isEmpty("   "));
		assertFalse(util.isEmpty("Gurpreet"));
	}

	@Test
	void testToUpperCase() {
		assertNull(util.toUpperCase(null));
		assertEquals("HELLO", util.toUpperCase("hello"));
		assertNotNull(util.toUpperCase("  "));
		assertNotNull(util.toUpperCase(""));
	}

	@Test
	void testGetLength() {
		assertEquals(8, util.getLength("Gurpreet"));
		assertEquals(0, util.getLength(""));
		assertEquals(3, util.getLength("   "));
		assertNotEquals(1, util.getLength(null));
	}

}
