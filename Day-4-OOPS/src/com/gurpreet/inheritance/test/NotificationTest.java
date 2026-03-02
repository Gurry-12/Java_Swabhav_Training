package com.gurpreet.inheritance.test;

import java.util.Scanner;
import com.gurpreet.inheritance.models.EmailNotification;
import com.gurpreet.inheritance.models.Notification;
import com.gurpreet.inheritance.models.PushNotification;
import com.gurpreet.inheritance.models.SMSNotification;
import com.gurpreet.helpers.ValidationHelper;
import com.gurpreet.helpers.Helpers;

public class NotificationTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Notification notification = null;
		
		System.out.println("========================================");
		System.out.println("     NOTIFICATION SYSTEM");
		System.out.println("========================================\n");

		boolean isRunning = true;
		do {

			displayDashboard();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();
			
			switch (choice) {
			case 1:
				handleEmailNotification(scanner, notification);
				break;

			case 2:
				handleSmsNotification(scanner, notification);
				break;

			case 3:
				handlePushNotification(scanner, notification);
				break;

			case 4:
				isRunning = false;
				System.out.println("\nThank you for using Notification System!");
				return;

			default:
				System.out.println("Please enter valid choice (1-4).\n");
			}

		} while (isRunning);

		scanner.close();
	}

	private static void handlePushNotification(Scanner scanner, Notification notification) {
		System.out.println("\n--- Push Notification ---");
		
		System.out.print("Enter recipient device ID: ");
		String to = ValidationHelper.validateDeviceId(scanner);

		System.out.print("Enter message: ");
		String message = ValidationHelper.validateMessage(scanner);

		notification = new PushNotification(to, message);
		notification.send();
		System.out.println("✓ Push alert sent successfully.\n");
	}

	private static void handleSmsNotification(Scanner scanner, Notification notification) {
		System.out.println("\n--- SMS Notification ---");
		ValidationHelper.displayMobileRules();
		
		System.out.print("Enter recipient mobile number: ");
		String to = ValidationHelper.validateMobileNumber(scanner);

		System.out.print("Enter message: ");
		String message = ValidationHelper.validateMessage(scanner);

		notification = new SMSNotification(to, message);
		notification.send();
		System.out.println("✓ SMS sent successfully.\n");
	}

	private static void handleEmailNotification(Scanner scanner, Notification notification) {
		System.out.println("\n--- Email Notification ---");
		ValidationHelper.displayEmailRules();
		
		System.out.print("Enter recipient email: ");
		String to = ValidationHelper.validateEmail(scanner);

		System.out.print("Enter message: ");
		String message = ValidationHelper.validateMessage(scanner);

		notification = new EmailNotification(to, message);
		notification.send();
		System.out.println("✓ Email sent successfully.\n");
	}

	private static void displayDashboard() {
		System.out.println("-----------------------------");
		System.out.println("     Notification Menu");
		System.out.println("1. Email Notification");
		System.out.println("2. SMS Notification");
		System.out.println("3. Push Alert Notification");
		System.out.println("4. Exit");
		System.out.println("-----------------------------");
	}
}
