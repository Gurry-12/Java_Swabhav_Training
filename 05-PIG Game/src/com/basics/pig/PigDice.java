package com.basics.pig;

import java.util.Random;
import java.util.Scanner;

public class PigDice {

	static final int TARGET_SCORE = 20;
	static final int PIG_ROLL = 1;
	static final int DIE_FACES = 6;

	/*
	 * Print the welcome message
	 */
	public static void printWelcome() {
		System.out.println("============================");
		System.out.println("      Let's Play PIG!       ");
		System.out.println("============================");
		System.out.println("* See how many turns it takes you to get to " + TARGET_SCORE + ".");
		System.out.println("* Turn ends when you hold or roll a 1.");
		System.out.println("* If you roll a 1, you lose all points for the turn.");
		System.out.println("* If you hold, you save all points for the turn.");
	}

	public static void printGameResult(int turnCount) {
		System.out.println("\nYou finished in " + turnCount + " turns!");
		System.out.println("Game over!");
	}

	/*
	 * Rolls the dice
	 */
	public static int rollDice(Random random) {
		return random.nextInt(DIE_FACES) + 1;
	}

	/*
	 * helper for valid choice
	 */
	public static String getValidChoice(Scanner scanner) {
		while (true) {
			System.out.print("Roll or hold? (r/h): ");
			String choice = scanner.next().toLowerCase();

			switch (choice) {
			case "r":
			case "h": {
				return choice;
			}
			default:
				System.out.println("Invalid input. Enter 'r' to roll or 'h' to hold.");
			}
		}
	}

	/*
	 * Play again method
	 */
	public static boolean askToPlayAgain(Scanner scanner) {
		while (true) {
			System.out.print("\nPlay again? (y/n): ");
			String input = scanner.next().toLowerCase();

			switch (input) {
			case "y": {
				return true;
			}
			case "n": {
				return false;
			}
			default:
				System.out.println("Invalid input. Enter 'y' or 'n'.");
			}
		}
	}

	/*
	 * Plays a single turn
	 */
	public static int playTurn(Scanner scanner, Random random, int turnNumber) {
		System.out.println("\nTURN " + turnNumber);

		int turnScore = 0;

		while (true) {
			String choice = getValidChoice(scanner);

			if (choice.equals("h")) {
				System.out.println("Score for turn: " + turnScore);
				return turnScore;
			}

			int die = rollDice(random);
			System.out.println("Die: " + die);

			if (die == PIG_ROLL) {
				System.out.println("Turn over. No score.");
				return 0;
			}

			turnScore += die;
		}
	}

	/*
	 * Main
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		printWelcome();

		boolean playAgain = true;

		while (playAgain) {

			int totalScore = 0;
			int turnCount = 0;

			while (totalScore < TARGET_SCORE) {
				turnCount++;
				int earned = playTurn(scanner, random, turnCount);
				totalScore += earned;

				if (earned > 0) {
					System.out.println("Total score: " + totalScore);
				}
			}

			printGameResult(turnCount);
			playAgain = askToPlayAgain(scanner);
		}

		System.out.println("Thanks for playing. Goodbye!");
		scanner.close();
	}
}