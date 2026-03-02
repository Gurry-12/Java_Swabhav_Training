package com.basics.objectcalisthenics;

import java.util.Scanner;

public class PrimeNumber {

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		
		// input no. 
		System.out.println("Enter a no.: ");
		int input = scanner.nextInt();
		
		scanner.close();
		
		if(input <= 1) {
			System.out.println(input + " is not a prime no.");
			return;
		}
		
		int count = 0;
		
		for(int i = 1 ; i <= input ; ++i) {
			if(input % i == 0) {
				count++;
			}
		}
		
//		if(count == 2) {
//			System.out.println(input + " is a prime no.");
//		} else {
//			System.out.println(input + " is not a prime no.");
//		}
		
		if(count != 2) {
			System.out.println(input + " is not a prime no.");
			return;
		}
		
		System.out.println(input + " is a prime no.");
		
	}
}
