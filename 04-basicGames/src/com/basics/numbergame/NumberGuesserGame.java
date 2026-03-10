package com.basics.numbergame;

import java.util.Random;
import java.util.Scanner;

public class NumberGuesserGame {

	// global declaration
	static Scanner scanner = new Scanner(System.in);

	public static int generateRandomNumber() {
		Random random = new Random();
		int generatedNum = random.nextInt(100) + 1;
		return generatedNum;
	}

	public static void guessNumberGame(int generatedNum) {

		int maxAttempts = 5;
		int countAttempts = 1;

		boolean isWon = false;

		while (countAttempts <= maxAttempts) {

			System.out.print("Guess a No.: ");
			int num = scanner.nextInt();

			// user no is greater
			if (num > generatedNum) {
				System.out.println("Sorry Too High");
			} else if (num < generatedNum) {
				System.out.println("Sorry Too Low");
			} else {
				isWon = true;
				System.out.println("You Won. with attempts: " + countAttempts);
				break;
			}

			countAttempts++;
		}

		if (!isWon) {
			System.out.println("You lose.");
		}
	}

	public static void main(String[] args) {

		boolean isGameStart = true;

		// start loop for game start
		while (isGameStart) {
			System.out.println("------------------------------------------");
			// method for game logic

			int generatedNum = generateRandomNumber();

			guessNumberGame(generatedNum);

			boolean isAsk = true;
			while (isAsk) {
				System.out.println("Do you want to play the game again: yes/ no ->");

				String input = scanner.next().toLowerCase();
				if (input.equals("yes")) {
					System.out.println("Let's Restart the game.");
					isAsk = false;
				} else if (input.equals("no")) {
					isGameStart = false;
					System.out.println("Thank you for playing.");
					isAsk = false;
				} else {
					System.out.println("Enter valid input.");
				}
			}

		}

		scanner.close();
	}
}
