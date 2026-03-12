package com.gurpreet.miniproject.tictactoe.models;

import com.gurpreet.miniproject.tictactoe.enums.State;

public class Board {
	private final Cell[][] cells = new Cell[3][3];

	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cells[i][j] = new Cell();
			}
		}
	}

	public void display() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + getSymbol(cells[i][j].getState()) + " ");
				if (j < 2)
					System.out.print("|");
			}
			System.out.println();
			if (i < 2) {
				System.out.println("-".repeat(3 * 4 - 1));
			}
		}
	}

	private String getSymbol(State state) {
		return switch (state) {
		case X -> "X";
		case O -> "O";
		case EMPTY -> " ";
		};
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