package com.gurpreet.abstractclass.models;

public class UPIPayment extends Payment {

	
	public UPIPayment(double amount) {
		super(amount);
	}

	@Override
	public void processPayment() {
		finalAmount = amount;
		System.out.println("Processing UPI payment with no fee.");
		
	}

}
