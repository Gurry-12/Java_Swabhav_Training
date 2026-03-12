package com.gurpreet.strings.test;

import java.util.Scanner;

import com.gurpreet.strings.models.PasswordPolicy;

public class PasswordTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Pasword checker ... \n");
		
		System.out.println("Enter your Password ");
		
		String password = scanner.nextLine();
		
		PasswordPolicy policy = new PasswordPolicy(password);
		
		policy.validatePassword();
		policy.output();
		
		scanner.close();
		
	}
}
