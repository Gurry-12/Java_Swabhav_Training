package com.gurpreet.interfaces.models;

import com.gurpreet.interfaces.interfaces.Payment;

public class UPIPayment implements Payment {

	@Override
	public void paymentProcess(double amount) {
		String rupee = "\u20B9";
		System.out.println("Processing UPI payment of " + rupee + amount);
        System.out.println("→ Instant transfer via UPI ID | Status: SUCCESS\n");
		
	}

}
