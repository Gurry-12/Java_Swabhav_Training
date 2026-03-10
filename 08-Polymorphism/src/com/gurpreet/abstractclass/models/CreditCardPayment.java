package com.gurpreet.abstractclass.models;

public class CreditCardPayment extends Payment {

	private static final double processFee = 0.02;
	public CreditCardPayment(double amount) {
		super(amount);
	}

	@Override
	public void processPayment() {
		finalAmount = amount + (amount * processFee);
		System.out.println("Processing Credit Card payment with 2% fee.");
		
	}

}
