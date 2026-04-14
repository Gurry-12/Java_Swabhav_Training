package com.gurpreet.facade.tictactoe.model;

import com.gurpreet.facade.tictactoe.model.enums.State;

public class Board {
	private final Cell[][] cells;
	private int size;
	
	public Board(int size) {
		this.size = size;
		this.cells = new Cell[size][size];
		createBoard(size);
	}

	private void createBoard(int size2) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cells[i][j] = new Cell();
			}
		}
	}

	public boolean isValidMove(int row, int col) {
		return row >= 0 && row < size && col >= 0 && col < size && cells[row][col].isEmpty();
	}

	public boolean markCell(int row, int col, State state) {
		if (isValidMove(row, col)) {
			cells[row][col].setState(state);
			return true;
		}
		return false;
	}

	public boolean isFull() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cells[i][j].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}

	public Cell getCell(int row, int col) {
		if (row >= 0 && row < size && col >= 0 && col < size) {
			return cells[row][col];
		}
		return null;
	}

	public int getSize() {
		return size;
	}
}