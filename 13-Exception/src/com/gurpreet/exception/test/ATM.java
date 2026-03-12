package com.gurpreet.exception.test;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		int balance = 10000;
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Enter the amount for withdrawal");
			int amount = validateInt(scanner);
			
			if(amount > balance) {
				throw new ArithmeticException();
			}
			
			balance -= amount;
            System.out.printf("Withdrawal successful. New balance: ₹%.2f%n", balance);
		}
		catch(ArithmeticException e) {
			System.out.println("Transaction failed:  Insufficient Balance");
		}
		finally {
			System.out.println("Transaction session ended.");
			scanner.close();
		}
		

	}
	
	private static int validateInt(Scanner scanner) {
		int input;
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.println("Enter a valid integer Input ");
				scanner.next();
				continue;
			}
			input = scanner.nextInt();
			break;
		}
		return input;
	}

}
