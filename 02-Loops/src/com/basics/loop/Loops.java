package com.basics.loop;

import java.util.Scanner;

public class Loops {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// loops - 
		// 1. printing 1 to 10
		int n = 1;
		while(n <= 10) {
			System.out.print(n + " ");
			n++;
		}
		System.out.println();
		
		// 2. printing odd numbers
		int m = 1;
		while(m <= 100) {
			System.out.print(m + " ");
			 m += 2;
		}
		System.out.println();
		
		m = 1;
		while(m <= 100 ) {
			if(m % 2 != 0 ) {
				System.out.print(m + " ");
			}
			m++;
		}
		System.out.println();
		System.out.println("Enter a no. : ");
		
		
		// 3. reverse a no. 
		int num = scanner.nextInt();
		System.out.println(num);
		int rev = 0;
		while(num > 0) {
			int a = num % 10;
			rev = (rev * 10) + a;
			num /= 10;
		}
		System.out.println(rev);
		
		scanner.close();
		
		
	}
}
