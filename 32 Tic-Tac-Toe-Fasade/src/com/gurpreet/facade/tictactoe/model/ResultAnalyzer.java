package com.gurpreet.facade.tictactoe.model;

import com.gurpreet.facade.tictactoe.model.enums.GameStatus;
import com.gurpreet.facade.tictactoe.model.enums.State;

public class ResultAnalyzer {

	private GameStatus status = GameStatus.ONGOING;

	public void checkWinner(Board board) {
		int size = board.getSize();

		// Check rows
		for (int i = 0; i < size; i++) {
			if (isLineWin(board, i, 0, 0, 1, size)) {
				status = GameStatus.WINNER;
				return;
			}
		}

		// Check columns
		for (int j = 0; j < size; j++) {
			if (isLineWin(board, 0, j, 1, 0, size)) {
				status = GameStatus.WINNER;
				return;
			}
		}

		// Check main diagonal (top-left to bottom-right)
		if (isDiagonalWin(board, size, false)) {
			status = GameStatus.WINNER;
			return;
		}

		// Check anti-diagonal (top-right to bottom-left)
		if (isDiagonalWin(board, size, true)) {
			status = GameStatus.WINNER;
			return;
		}

		status = board.isFull() ? GameStatus.DRAW : GameStatus.ONGOING;
	}

	public GameStatus getStatus() {
		return status;
	}

	private boolean isLineWin(Board board, int startRow, int startCol, int rowStep, int colStep, int size) {
		State first = board.getCell(startRow, startCol).getState();
		if (first == State.EMPTY)
			return false;

		for (int k = 1; k < size; k++) {
			if (board.getCell(startRow + k * rowStep, startCol + k * colStep).getState() != first) {
				return false;
			}
		}
		return true;
	}

	private boolean isDiagonalWin(Board board, int size, boolean antiDiagonal) {
		State first = antiDiagonal ? board.getCell(0, size - 1).getState() : board.getCell(0, 0).getState();

		if (first == State.EMPTY)
			return false;

		for (int k = 1; k < size; k++) {
			int col = antiDiagonal ? (size - 1 - k) : k;
			if (board.getCell(k, col).getState() != first) {
				return false;
			}
		}
		return true;
	}
}