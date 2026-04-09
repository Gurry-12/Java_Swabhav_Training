package com.gurpreet.facade.tictactoe.model.facade;

import com.gurpreet.facade.tictactoe.model.Board;
import com.gurpreet.facade.tictactoe.model.ResultAnalyzer;
import com.gurpreet.facade.tictactoe.model.enums.GameStatus;
import com.gurpreet.facade.tictactoe.model.exception.InvalidMoveException;
import com.gurpreet.facade.tictactoe.model.helpers.TicTacToeUtil;
import com.gurpreet.facade.tictactoe.model.player.Player;

public class GameEngine {

	private final Board board;
	private final Player playerX;
	private final Player playerO;
	private Player currentPlayer;
	private final ResultAnalyzer analyzer;

	// Dependency Injection: The Engine no longer constructs its own dependencies!
	public GameEngine(Board board, ResultAnalyzer analyzer, Player playerX, Player playerO) {
		this.board = board;
		this.analyzer = analyzer;
		this.playerX = playerX;
		this.playerO = playerO;
		this.currentPlayer = playerX;
	}

	public void gameStart() throws InvalidMoveException {
		while (true) {
			TicTacToeUtil.display(board);

			System.out.println(
					currentPlayer.getName() + "'s turn (" + currentPlayer.getState() + ")");

			// Get move from current player
			int[] move = currentPlayer.getNextMove(board);

			// Process the move
			processMove(move[0], move[1]);

			// Check game status
			analyzer.checkWinner(board);
			GameStatus status = getStatus();

			if (status != GameStatus.ONGOING) {
				TicTacToeUtil.display(board);
				if (status == GameStatus.DRAW) {
					System.out.println("It's a Draw!");
				} else {
					System.out.println(currentPlayer.getName() + " Wins!");
				}
				System.out.println("Thank you for playing Tic Tac Toe.");
				break;
			}

			// Switch turn
			switchTurn();
		}
	}

	public void processMove(int row, int col) throws InvalidMoveException {
		if (!board.isValidMove(row, col)) {
			throw new InvalidMoveException("Invalid move at position (" + row + ", " + col + "). The position is either out of bounds or already occupied.");
		}
		board.markCell(row, col, currentPlayer.getState());
	}

	public void switchTurn() {
		currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
	}

	public GameStatus getStatus() {
		return analyzer.getStatus();
	}

}