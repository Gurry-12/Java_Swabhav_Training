package com.gurpreet.inheritance.models;

public class Notification {
	private String recipient;
	private String message;
	
	public Notification(String recipient, String message) {
		if(recipient == null || recipient.trim().isEmpty()) {
			System.out.println("Recipient cannot be Empty.");
			return;
		}
		
		if(message == null || message.trim().isEmpty()) {
			System.out.println("message cannot be Empty.");
			return;
		}
		this.message = message;
		this.recipient = recipient;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void send() {
		System.out.println("Sending notification...");
	}
		
	protected void logNotification() {
        System.out.println("Logging notification to " + recipient + ": " + message);
    }
	
}
