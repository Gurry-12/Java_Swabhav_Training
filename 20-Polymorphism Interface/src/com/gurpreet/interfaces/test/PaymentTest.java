package com.gurpreet.interfaces.test;

import java.util.Scanner;

import com.gurpreet.helpers.Helpers;
import com.gurpreet.interfaces.interfaces.Payment;
import com.gurpreet.interfaces.models.CreditCardPayment;
import com.gurpreet.interfaces.models.DebitCardPayment;
import com.gurpreet.interfaces.models.UPIPayment;

public class PaymentTest {

	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=======================================");
		System.out.println("==    Welcome to the Payment World.  ==");
		System.out.println("=======================================");
		
		boolean isRunning = true;
		
		while(isRunning) {
			
			displayMenu();
			
			System.out.println("Enter your input ");

			int input = Helpers.validateInt(scanner);
			
			switch(input) {
			
			case 1: 
				createPayment(scanner);
				break;
				
			case 2: 
				isRunning = false;
				System.out.println("Thank you for visiting");
				break;
				
			default:
				System.out.println("Enter valid input 1-2");
			}
			
			
	}	
			
	}

	private static void createPayment(Scanner scanner) {
		
		System.out.println("Enter the amount you want to pay");
		double amount = Helpers.validateDoubleNoNegative(scanner);
		
		displayDashboard();
		
		System.out.println("Enter your Choice : ");
		
		int choice = Helpers.validateInt(scanner);
		
		switch(choice) {
		
		case 1:
			createCreaditCardPayment(amount);
			break;
			
		case 2:
			createDebitCardPayment(amount);
			break;
		
		case 3:
			createUPIPayment(amount);
			break;
			
		default:
			System.out.println("Please enter valid input 1-3");
		}
	}
		

	private static void displayMenu() {
		System.out.println("----------------------");
		System.out.println("1. Pay Ammount");
		System.out.println("2. Exit");
		
	}

	private static void createUPIPayment(double amount) {
		Payment payment = new UPIPayment();
		payment.paymentProcess(amount);
		
	}

	private static void createDebitCardPayment(double amount) {
		Payment payment  = new DebitCardPayment();
		payment.paymentProcess(amount);
		
	}

	private static void createCreaditCardPayment(double amount) {
		Payment payment  = new CreditCardPayment();
		payment.paymentProcess(amount);
		
	}

	private static void displayDashboard() {
		System.out.println("--------------------------------------");
		System.out.println("1. : CreditCard Payment");
		System.out.println("2. : DebitCard Payment");
		System.out.println("3. : UPI Payment");
		
	}
	
}
