package com.gurpreet.miniproject.tictactoe.test;

import java.util.Scanner;
import com.gurpreet.miniproject.tictactoe.helpers.Helpers;
import com.gurpreet.miniproject.tictactoe.models.Game;

public class TicTacToeTest {
	public static void main(String[] args) {
		// Only object creation and start() call
		Scanner scanner = new Scanner(System.in);
		Game game = new Game();
		boolean isGameOn = true;

		while (isGameOn) {

			displayMenu();

			int choice = Helpers.validateInt(scanner);
			switch (choice) {

			case 1:
				game.start();
				break;

			case 2:
				isGameOn = false;
				System.out.println("Thank you for play. wish you the best.");
				break;

			default:
				System.out.println("Enter the valid input [1-2]");
			}

		}

		scanner.close();

	}

	private static void displayMenu() {
		System.out.println("Welcome to the tic tac toe World.");
		System.out.println("Press 1. for play game.");
		System.out.println("Press 2. for exit");

	}
}