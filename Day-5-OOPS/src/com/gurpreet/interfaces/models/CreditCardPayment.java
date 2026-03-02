package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.Payment;

public class CreditCardPayment implements Payment {

	@Override
	public void paymentProcess(double amount) {
		String rupee = "\u20B9";
		System.out.println("Processing credit card payment of " + rupee + amount);
        System.out.println("→ Card charged | Transaction fee: 2.5% | Status: APPROVED\n");
		
	}

}
