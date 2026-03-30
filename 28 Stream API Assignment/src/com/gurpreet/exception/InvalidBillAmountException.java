package com.gurpreet.exception;

public class InvalidBillAmountException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidBillAmountException(String message) {
		super(message);
	}
}