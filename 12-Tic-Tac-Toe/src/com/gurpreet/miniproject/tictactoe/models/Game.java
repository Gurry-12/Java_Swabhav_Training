package com.gurpreet.miniproject.tictactoe.models;

import java.util.Scanner;

import com.gurpreet.miniproject.tictactoe.enums.State;
import com.gurpreet.miniproject.tictactoe.helpers.Helpers;

public class Game {
	private final Board board;
	private final ResultAnalyzer analyzer;
	private Player playerX;
	private Player playerO;
	private Player currentPlayer;
	private final Scanner scanner;

	public Game() {
		this.board = new Board();
		this.analyzer = new ResultAnalyzer(board);
		this.scanner = new Scanner(System.in);
	}

	public void start() {
		System.out.println("=================================");
		System.out.println("     TIC TAC TOE - Console      ");
		System.out.println("=================================");

		System.out.println("Choose game mode:");
		System.out.println("1. Human vs Human");
		System.out.println("2. Human vs AI (Computer)");
		System.out.print("Enter your choice: ");

		int mode = getValidChoice();

		switch (mode) {

		case 1: {
			System.out.print("Enter name for Player X: ");
			String nameX = Helpers.validateString(scanner);
			System.out.print("Enter name for Player O: ");
			String nameO = Helpers.validateString(scanner);

			playerX = new HumanPlayer(nameX.isEmpty() ? "Player X" : nameX, State.X, scanner);
			playerO = new HumanPlayer(nameO.isEmpty() ? "Player O" : nameO, State.O, scanner);
			break;
		}
		case 2: {
			System.out.print("Enter your name (you play as X): ");
			String humanName = Helpers.validateString(scanner);
			playerX = new HumanPlayer(humanName.isEmpty() ? "You" : humanName, State.X, scanner);
			playerO = new AIPlayer("Computer (O)", State.O);
			break;
		}
		default:
			System.out.println("Enter valid Input 1-2");
		}
		currentPlayer = playerX;
		System.out.println("\nGame Started! " + playerX.getState() + " goes first.\n");

		playGame();
		scanner.close();
	}

	private void playGame() {
		while (true) {
			board.display();
			System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getState() + ")");

			int[] move = currentPlayer.getNextMove(board);
			board.markCell(move[0], move[1], currentPlayer.getState());

			String result = analyzer.analyze();

			if (!result.equals("CONTINUE")) {
				board.display();
				if (result.equals("DRAW")) {
					System.out.println("It's a Draw!");
				} else {
					System.out.println(result + " !");
				}
				System.out.println("Thank you for playing Tic Tac Toe.");
				break;
			}

			currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
		}
	}

	private int getValidChoice() {
		while (true) {
			if (scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				scanner.nextLine();
				if (choice == 1 || choice == 2) {
					return choice;
				}
			} else {
				scanner.nextLine();
			}
			System.out.print("Please enter 1 or 2: ");
		}
	}
	
}
