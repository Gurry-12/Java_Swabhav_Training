package com.basics.loop;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        int reversed = 0;
        int temp = number;
        
        while (temp != 0) {
            int digit = temp % 10;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }
        
        System.out.println("Original number: " + number);
        System.out.println("Reversed number: " + reversed);
        
        scanner.close();
    }
}
