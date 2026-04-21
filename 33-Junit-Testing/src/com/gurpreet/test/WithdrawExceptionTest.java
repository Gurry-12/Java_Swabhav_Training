package com.gurpreet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gurpreet.model.BankAccount;

/*
 *  Assignment 10 - Test Withdraw Exceptions 
 */
class WithdrawExceptionTest {

	BankAccount account = new BankAccount(0);

	@Test
	void shouldThrowExceptionWhenAmountIsGreaterThanBalance() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			account.withdraw(1000.0, 1500.0);
		});
		assertEquals("Insufficient balance", exception.getMessage()); 
	}

	@Test
	void shouldThrowExceptionWhenAmountIsZeroOrNegative() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			account.withdraw(100.0, 0.0);
		});
		assertEquals("Amount must be positive", exception.getMessage());
	}
}