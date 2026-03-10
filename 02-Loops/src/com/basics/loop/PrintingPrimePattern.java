package com.basics.loop;

import java.util.Scanner;

public class PrintingPrimePattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        
        int count = 0;
        int num = 2;
        
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                while (!isPrime(num)) {
                    num++;
                }
                System.out.print(num + " ");
                num++;
                count++;
            }
            System.out.println();
        }
        
        scanner.close();
    }
    
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
