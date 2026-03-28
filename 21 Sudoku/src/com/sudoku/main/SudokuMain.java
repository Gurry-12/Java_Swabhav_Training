package com.sudoku.main;

import java.util.Scanner;
import com.sudoku.enums.GameLevel;
import com.sudoku.models.SudokuGame;

public class SudokuMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean playAgain = true;

		while (playAgain) {
			GameLevel gameDifficulty = getGameDifficulty(scanner);
			SudokuGame game = new SudokuGame(scanner, gameDifficulty);
			game.start();
			playAgain = askPlayAgain(scanner);
		}

		System.out.println("Thanks for playing. Goodbye!");
		scanner.close();
	}

	private static boolean askPlayAgain(Scanner scanner) {
		while (true) {
			System.out.print("\nPlay again? (y/n): ");
			String userChoice = scanner.nextLine().trim().toLowerCase();

			if (userChoice.equals("y"))
				return true;
			if (userChoice.equals("n"))
				return false;

			System.out.println("Please enter 'y' or 'n'.");
		}
	}

	private static GameLevel getGameDifficulty(Scanner scanner) {
		System.out.println("Choose the Difficulty:");
		GameLevel[] levels = GameLevel.values();

		for (int i = 0; i < levels.length; i++) {
			System.out.println((i + 1) + ": " + levels[i]);
		}

		int choice = validateIntRange(scanner, 1, levels.length);
		return levels[choice - 1];
	}

	private static int validateIntRange(Scanner scanner, int min, int max) {
		int value;
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid integer: ");
				scanner.next();
				continue;
			}
			value = scanner.nextInt();
			scanner.nextLine();
			if (value < min || value > max) {
				System.out.printf("Please enter a number between %d and %d: ", min, max);
				continue;
			}
			return value;
		}
	}
}
