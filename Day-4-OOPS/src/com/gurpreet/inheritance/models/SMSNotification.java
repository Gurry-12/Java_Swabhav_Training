package com.gurpreet.inheritance.models;

public class SMSNotification extends Notification {

	public SMSNotification(String recipient, String message) {
		super(recipient, message);
		
	}

	@Override
	public void send() {
		super.logNotification();
		System.out.println("Message sent to " + getRecipient() + ": " + getMessage());
	}
}
