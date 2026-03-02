package com.gurpreet.abstractclass.models;

public class WalletPayment extends Payment{

	private static final double processFee = 0.01;
	public WalletPayment(double amount) {
		super(amount);
	}

	@Override
	public void processPayment() {
		finalAmount = amount + (amount * processFee);
		System.out.println("Processing Wallet payment with 1% fee.");
		
	}

}
