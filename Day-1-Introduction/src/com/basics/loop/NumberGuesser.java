package com.basics.loop;

import java.util.Random;
import java.util.Scanner;

public class NumberGuesser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 10;
        boolean hasWon = false;
        
        System.out.println("Welcome to Number Guesser Game!");
        System.out.println("I'm thinking of a number between 1 and 100");
        System.out.println("You have " + maxAttempts + " attempts to guess it");
        
        for (int i = 0; i < maxAttempts; i++) {
            System.out.print("\nAttempt " + (i + 1) + ": Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;
            
            if (guess == numberToGuess) {
                hasWon = true;
                break;
            } else if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }
        
        if (hasWon) {
            System.out.println("\nCongratulations! You guessed it in " + attempts + " attempts!");
        } else {
            System.out.println("\nGame Over! The number was: " + numberToGuess);
        }
        
        scanner.close();
    }
}
