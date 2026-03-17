package com.sudoku.models;

public class SudokuValidator extends GridValidator {

	public SudokuValidator(SudokuBoard board) {
		super(board);
	}

	@Override
	public boolean validate() {

		if (!checkRows()) {
			return false;
		}

		if (!checkColumns()) {
			return false;
		}

		if (!checkBoxes()) {
			return false;
		}

		result = "Sudoku is Valid";
		return true;
	}

	private boolean checkRows() {
		int[][] grid = board.getGrid();

		for (int r = 0; r < 9; r++) {

			int[] arr = grid[r]; 

			if (hasDuplicate(arr)) {
				result = "Duplicate found in row " + (r + 1);
				return false;
			}
		}

		return true;
	}

	private boolean checkColumns() {
		int[][] grid = board.getGrid();

		for (int c = 0; c < 9; c++) {

			int[] arr = new int[9];

			for (int r = 0; r < 9; r++) {
				arr[r] = grid[r][c]; 
			}

			if (hasDuplicate(arr)) {
				result = "Duplicate found in column " + (c + 1);
				return false;

			}
		}
		return true;
	}

	private boolean checkBoxes() {
		int[][] grid = board.getGrid();

		for (int boxRow = 0; boxRow < 9; boxRow += 3) {
			for (int boxCol = 0; boxCol < 9; boxCol += 3) {

				int[] arr = new int[9];
				int idx = 0;

				for (int r = boxRow; r < boxRow + 3; r++) {
					for (int c = boxCol; c < boxCol + 3; c++) {
						arr[idx++] = grid[r][c]; 
					}
				}

				if (hasDuplicate(arr)) {
					result = "Duplicate found in box at row " + (boxRow + 1) + " col " + (boxCol + 1);
					return false;
				}
			}
		}

		return true;
	}

}
