package com.gurpreet.strings.test;

import java.util.Scanner;
import com.gurpreet.strings.models.EmailAnalyzer;

public class EmailAnalyzerTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("--------------------------------\n" + "	Email Analyzer\n"
				+ "--------------------------------");
		
		System.out.println("\n Enter the Email.");
		
		String email = scanner.nextLine().trim();   //task 1
		
		if(!email.contains("@")) {     // task 2
			System.out.println("Email do not contain the @ symbol");
			scanner.close();
			return;
		}
		
		EmailAnalyzer analyzer = new EmailAnalyzer(email);
		analyzer.extractUserNameAndDomain(); 
		analyzer.output();
		
		scanner.close();
	}

}
