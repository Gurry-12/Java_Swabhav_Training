package com.gurpreet.facade.tictactoe.model;

import com.gurpreet.facade.tictactoe.model.enums.State;

public class Board {
	private final Cell[][] cells = new Cell[3][3];

	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cells[i][j] = new Cell();
			}
		}
	}

	public boolean isValidMove(int row, int col) {
		return row >= 0 && row < 3 && col >= 0 && col < 3 && cells[row][col].isEmpty();
	}

	public boolean markCell(int row, int col, State state) {
		if (isValidMove(row, col)) {
			cells[row][col].setState(state);
			return true;
		}
		return false;
	}

	public boolean isFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cells[i][j].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}

	public Cell getCell(int row, int col) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			return cells[row][col];
		}
		return null;
	}
}