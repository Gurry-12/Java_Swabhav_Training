package com.gurpreet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.gurpreet.model.User;


class UserTest {

	private User user;

	@BeforeEach
	void setUp() {
		user = new User("Gurpreet", 21);
		System.out.println("Setting up new User object...");
	}

	@AfterEach
	void tearDown() {
		user = null;
		System.out.println("Test complete. Cleaning up...");
	}

	@Test
	void testUserValid() {
		assertTrue(user.isValid());
	}

	@Test
	void testUserName() {
		assertEquals("Gurpreet", user.getName());
	}

	@ParameterizedTest
	@CsvSource(value = {
		"NULL, 25",
		"Ram, -1",
		"Mohan, 150"		
	}, nullValues = "NULL")
	void testInvalidUser(String name, int age) {
		user = new User(name, age);
		assertFalse(user.isValid());
	}

	@Test
	@Disabled
	void testNullUserName() {
		User nullUser = new User(null, 25);
		assertFalse(nullUser.isValid());
	}

	@Test
	@Disabled
	void testUserNegativeAge() {
		User youngUser = new User("Ram", -1);
		assertFalse(youngUser.isValid());
	}

	@Test
	@Disabled
	void testUserAgeIsTooHigh() {
		User oldUser = new User("Old", 150);
		assertFalse(oldUser.isValid());
	}
}