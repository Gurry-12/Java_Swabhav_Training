package com.sudoku.validators;

import com.sudoku.exceptions.SudokuException;
import com.sudoku.models.SudokuBoard;

public abstract class GridValidator {

	protected SudokuBoard board;

	public GridValidator(SudokuBoard board) {
		this.board = board;
	}

	public abstract void validate() throws SudokuException;

	public SudokuBoard getBoard() {
		return board;
	}

	protected void validateGridStructure() throws SudokuException {
		int[][] grid = board.getGrid();

		if (grid == null) {
			throw new SudokuException("Grid is null.");
		}

		if (grid.length != 9) {
			throw new SudokuException("Grid must have 9 rows. Found: " + grid.length + ".");
		}

		for (int r = 0; r < 9; r++) {
			if (grid[r] == null || grid[r].length != 9) {
				throw new SudokuException("Row " + (r + 1) + " is missing or incomplete.");
			}

			for (int c = 0; c < 9; c++) {
				if (grid[r][c] < 0 || grid[r][c] > 9) {
					throw new SudokuException(
							"Cell at row " + (r + 1) + " col " + (c + 1) + " has illegal value: " + grid[r][c] + ".");
				}
			}
		}
	}

	protected int findDuplicate(int[] arr) {
		boolean[] seen = new boolean[10];

		for (int num : arr) {
			if (num == 0)
				continue;
			if (num < 1 || num > 9)
				return num;
			if (seen[num])
				return num;
			seen[num] = true;
		}
		return 0;
	}

	protected int countEmptyCells() {
		int[][] grid = board.getGrid();
		int empty = 0;

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (grid[r][c] == 0)
					empty++;
			}
		}
		return empty;
	}
}