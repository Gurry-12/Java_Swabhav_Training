package com.basics.numbergame;

import java.util.Random;
import java.util.Scanner;

public class NumberGuesserGame {

	/*
	 * generate random number method
	 */
	public static int generateRandomNumber(Random random) {
		return random.nextInt(100) + 1;
	}

	/*
	 * number guess game logic
	 */
	public static void guessNumberGame(Scanner scanner, int generatedNum) {

		int maxAttempts = 5;
		int countAttempts = 1;

		boolean isWon = false;

		while (countAttempts <= maxAttempts) {

			System.out.println(
					"Guess a number between 1 and 100 (Attempts left: " + (maxAttempts - countAttempts + 1) + "):");
			int num = validInputInRange(scanner);

			if (num > generatedNum) {
				System.out.println("Sorry Too High");
				countAttempts++;
				continue;
			}
			if (num < generatedNum) {
				System.out.println("Sorry Too Low");
				countAttempts++;
				continue;
			}

			isWon = true;
			System.out.println("You Won in attempts: " + countAttempts);
			break;

		}

		if (!isWon) {
			System.out.println("You lose. The number was: " + generatedNum);
		}
	}

	/*
	 * main method
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		boolean playAgain = true;

		System.out.println("Welcome to Number Guesser World. ");
		// game start loop
		while (playAgain) {
			System.out.println("------------------------------------------");

			int generatedNum = generateRandomNumber(random);

			guessNumberGame(scanner, generatedNum);

			playAgain = askToPlayAgain(scanner);
			if (playAgain) {
				System.out.println("Let's play again! Good luck!");
			}
		}

		scanner.close();
	}

	/*
	 * ask to play Again method
	 */
	private static boolean askToPlayAgain(Scanner scanner) {
		boolean validInput = true;
		boolean playAgain = false;

		System.out.println("Do you want to play again? ");
		System.out.println("1. for Play Again");
		System.out.println("2. for Exit");

		while (validInput) {
			System.out.println("Enter your Choice.");

			int choice = validateInt(scanner);

			switch (choice) {

			case 1:
				playAgain = true;
				validInput = false;
				break;
			case 2:
				System.out.println("Thanks for visiting");
				validInput = false;
				break;

			default:
				System.out.println("Please enter from the valid range [1,2]");

			}

		}

		return playAgain;
	}

	/*
	 * Helper for the validate integer
	 */
	private static int validateInt(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println("Wrong input, Enter a valid integer Input ");
			scanner.next();
		}

		return scanner.nextInt();
	}

	/*
	 * Helper for validate range
	 */

	private static int validInputInRange(Scanner scanner) {
		int input;
		while (true) {
			input = validateInt(scanner);

			if (input < 1 || input > 100) {
				System.out.println("Wrong input, Enter the no. in the range [1, 100]");
				continue;
			}
			break;
		}
		return input;
	}

}