package com.basics.introduction;

import java.util.Scanner;


public class UserInput {
    
public static void main(String[] args) {
	
	Scanner scanner = new Scanner(System.in);
	
	System.out.println("Enter a String.");
	String string = scanner.nextLine();
	System.out.println(string);
	
	System.out.println("Enter Interger");
	int integer = scanner.nextInt();
	System.out.println(integer);
	
	System.out.println("Enter double");
	double doubleValue = scanner.nextDouble();
	System.out.println(doubleValue);
	
	scanner.close();
}
	
	
    
}
