package com.basics.introduction;

import java.util.Scanner;


public class MathOperations {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the Math world.");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter 1st no for check max and min ");
		double number1 = scanner.nextDouble();
		
		System.out.println("Enter 2nd no for check max and min ");
		double number2 = scanner.nextDouble();
		
		System.out.println("Max : " + Math.max(number1, number2));
		System.out.println("Max : " + Math.min(number1, number2));
		
		System.out.println("Enter a no for check absolute value ");
		
		double number3 = scanner.nextDouble();
		
		System.out.println("Abs : " + Math.abs(number3));
		
		System.out.println("Enter a no for check sqrt ");
		double number4 = scanner.nextDouble();
		
		System.out.println("Sqrt : " + Math.sqrt(number4));
		
		System.out.println("Enter a no to check power in term x ^ y so enter x : ");
		double number5 = scanner.nextDouble();
		
		System.out.println("Enter a no to check power in term x ^ y so enter y : ");
		double number6 = scanner.nextDouble();
		
		System.out.println("Power : " + Math.pow(number5, number6));
		
		System.out.println("Enter a value to check Floor , Ceil and Round: ");
		
		double number7 = scanner.nextDouble();
		
		System.out.println("Round : " + Math.round(number7));
		System.out.println("Ceil : " + Math.ceil(number7));
		System.out.println("Floor : " + Math.floor(number7));
		
		System.out.println("Generate a Random :");
		System.out.println("Random : " + Math.random());
		
		scanner.close();
	}

}
