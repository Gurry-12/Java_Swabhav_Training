package com.basics.loop;

public class PrintOddNumbers {
    public static void main(String[] args) {
        System.out.println("Odd numbers between 1 to 100:");
        
        int i = 1;
        while (i <= 100) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
            i++;
        }
        System.out.println();
    }
}
