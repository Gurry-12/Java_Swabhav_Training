package com.gurpreet.inheritance.models;

public class PushNotification extends Notification {

	public PushNotification(String recipient, String message) {
		super(recipient, message);
	}
	
	@Override
	public void send() {
		super.logNotification();
		System.out.println("Push alert sent to " + getRecipient() + ": " + getMessage());
	}

}
