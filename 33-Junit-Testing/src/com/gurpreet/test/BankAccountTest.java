package com.gurpreet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gurpreet.model.BankAccount;

class BankAccountTest {

	private BankAccount account;

	@BeforeEach
	void setUp() {
		account = new BankAccount(1000.0);
	}


	@Test
	void testDepositAmount() {
		account.deposit(500.0);
		assertEquals(1500.0, account.getBalance());
	}

	@Test
	void testWithdrawAmount() {
		account.withdraw(300.0);
		assertEquals(700.0, account.getBalance());
	}


	@ParameterizedTest
	@ValueSource( doubles = {1200.0, -1000})
	void testWithdrawInsufficientBalance(double amount) {
		assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
	}

	@Test
	void testDepositNegativeAmount() {
		assertThrows(IllegalArgumentException.class , () -> account.deposit(-500.0));
	}

	@Test
	void testGetBalance() {
		assertEquals(1000.0, account.getBalance());
	}
	
	@Test
	void testNegativeInitialBalance() {
		assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1000.0));
	}
}