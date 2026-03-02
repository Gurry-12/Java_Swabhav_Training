package com.basics.loop;

import java.util.Scanner;

public class PerfectNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        if (isPerfect(number)) {
            System.out.println(number + " is a perfect number");
        } else {
            System.out.println(number + " is not a perfect number");
        }
        
        scanner.close();
    }
    
    public static boolean isPerfect(int num) {
        if (num <= 1) {
            return false;
        }
        
        int sum = 0;
        int i = 1;
        
        while (i <= num / 2) {
            if (num % i == 0) {
                sum += i;
            }
            i++;
        }
        
        return sum == num;
    }
}
