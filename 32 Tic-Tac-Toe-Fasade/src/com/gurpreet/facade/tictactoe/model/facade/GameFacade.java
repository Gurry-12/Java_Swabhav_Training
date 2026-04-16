package com.gurpreet.facade.tictactoe.model.facade;

import java.util.Scanner;

import com.gurpreet.facade.tictactoe.model.Board;
import com.gurpreet.facade.tictactoe.model.ResultAnalyzer;
import com.gurpreet.facade.tictactoe.model.enums.BoardSize;
import com.gurpreet.facade.tictactoe.model.enums.State;
import com.gurpreet.facade.tictactoe.model.exception.InvalidModeException;
import com.gurpreet.facade.tictactoe.model.exception.InvalidMoveException;
import com.gurpreet.facade.tictactoe.model.player.AIPlayer;
import com.gurpreet.facade.tictactoe.model.player.HumanPlayer;
import com.gurpreet.facade.tictactoe.model.player.Player;

public class GameFacade {

	private GameEngine engine;
	private final Scanner scanner;
	private final GameMenu menu;

	public GameFacade() {
		this.scanner = new Scanner(System.in);
		this.menu = new GameMenu(scanner);
	}

	public void start() {
		boolean isGameOn = true;

		while (isGameOn) {
			int choice;
			try {
				choice = menu.showMainMenu();

				switch (choice) {
				case 1:
					int size = menu.selectBoardSize();
					initializeGame(size);
					break;

				case 2:
					isGameOn = false;
					scanner.close();
					System.out.println("Goodbye!");
					break;

				default:
					System.out.println("Please enter a valid choice (1 or 2).");
				}
			} catch (InvalidModeException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private void initializeGame(int size) {
		boolean validInputMode = false;

		while (!validInputMode) {

			try {
				int mode = menu.chooseGameMode();

				switch (mode) {
				case 1:
					startHumanVsHuman(size);
					validInputMode = true;
					break;

				case 2:
					startHumanVsAI(size);
					validInputMode = true;
					break;

				default:
					System.out.println("Invalid Option Selected. Mode must be 1 or 2.");
				}
			} catch (InvalidModeException e) {
				System.out.println(e.getMessage());
			} catch (InvalidMoveException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void startHumanVsHuman(int size) throws InvalidMoveException {
		String nameX = menu.getPlayerName("Enter name for Player X: ");
		String nameO = menu.getPlayerName("Enter name for Player O: ");

		Board board = new Board(size);
		ResultAnalyzer analyzer = new ResultAnalyzer();
		Player playerX = new HumanPlayer(nameX, State.X, scanner);
		Player playerO = new HumanPlayer(nameO, State.O, scanner);

		this.engine = new GameEngine(board, analyzer, playerX, playerO);
		engine.gameStart();
	}

	private void startHumanVsAI(int size) throws InvalidMoveException {
		String humanName = menu.getPlayerName("Enter your name (you play as X): ");

		Board board = new Board(size);
		ResultAnalyzer analyzer = new ResultAnalyzer();
		Player playerX = new HumanPlayer(humanName, State.X, scanner);
		Player playerO = new AIPlayer("Computer (O)", State.O);

		this.engine = new GameEngine(board, analyzer, playerX, playerO);
		engine.gameStart();
	}
}
