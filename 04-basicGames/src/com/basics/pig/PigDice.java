package com.basics.pig;

import java.util.Random;
import java.util.Scanner;

public class PigDice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int totalScore = 0;
        int targetScore = 100;
        
        System.out.println("Welcome to Pig Dice Game!");
        System.out.println("First to reach " + targetScore + " points wins!");
        
        while (totalScore < targetScore) {
            int turnScore = 0;
            boolean turnActive = true;
            
            System.out.println("\n--- New Turn ---");
            System.out.println("Total Score: " + totalScore);
            
            for (; turnActive; ) {
                System.out.print("Roll dice? (y/n): ");
                String choice = scanner.next().toLowerCase();
                
                if (choice.equals("y")) {
                    int roll = random.nextInt(6) + 1;
                    System.out.println("You rolled: " + roll);
                    
                    if (roll == 1) {
                        System.out.println("Oops! You rolled a 1. Turn over!");
                        turnScore = 0;
                        turnActive = false;
                    } else {
                        turnScore += roll;
                        System.out.println("Turn score: " + turnScore);
                    }
                } else {
                    totalScore += turnScore;
                    System.out.println("You banked " + turnScore + " points!");
                    turnActive = false;
                }
            }
        }
        
        System.out.println("\n*** Congratulations! You won with " + totalScore + " points! ***");
        scanner.close();
    }
}
