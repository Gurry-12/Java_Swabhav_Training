package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.Payment;

public class DebitCardPayment implements Payment {

	@Override
	public void paymentProcess(double amount) {
		String rupee = "\u20B9";
		System.out.println("Processing debit card payment of " + rupee + amount);
        System.out.println("→ Direct bank transfer | No extra fee | Status: APPROVED\n");
		
	}

}
