package com.sudoku.validators;

import com.sudoku.exceptions.SudokuException;
import com.sudoku.models.SudokuBoard;

public class SudokuValidator extends GridValidator {

	public SudokuValidator(SudokuBoard board) {
		super(board);
	}

	@Override
	public void validate() throws SudokuException {
		validateGridStructure();

		// Partial board is allowed — we validate what's there.
		// If cells are empty we still check filled ones for conflicts.
		int emptyCells = countEmptyCells();
		if (emptyCells > 0) {
			System.out.println("Note: Board is incomplete (" + emptyCells + " cells empty). "
					+ "Checking filled cells for conflicts.");
		}

		checkRows();
		checkColumns();
		checkBoxes();
	}

	private void checkRows() throws SudokuException {
		int[][] grid = board.getGrid();

		for (int r = 0; r < 9; r++) {
			int duplicate = findDuplicate(grid[r]);
			if (duplicate != 0) {
				throw new SudokuException("Value " + duplicate + " appears more than once in row " + (r + 1) + ".");
			}
		}
	}

	private void checkColumns() throws SudokuException {
		int[][] grid = board.getGrid();

		for (int c = 0; c < 9; c++) {
			int[] colValues = new int[9];

			for (int r = 0; r < 9; r++) {
				colValues[r] = grid[r][c];
			}

			int duplicate = findDuplicate(colValues);
			if (duplicate != 0) {
				throw new SudokuException("Value " + duplicate + " appears more than once in column " + (c + 1) + ".");
			}
		}
	}

	private void checkBoxes() throws SudokuException {
		int[][] grid = board.getGrid();

		for (int boxRow = 0; boxRow < 9; boxRow += 3) {
			for (int boxCol = 0; boxCol < 9; boxCol += 3) {

				int[] boxValues = new int[9];
				int index = 0;

				for (int r = boxRow; r < boxRow + 3; r++) {
					for (int c = boxCol; c < boxCol + 3; c++) {
						boxValues[index++] = grid[r][c];
					}
				}

				int duplicate = findDuplicate(boxValues);
				if (duplicate != 0) {
					throw new SudokuException("Value " + duplicate + " appears more than once in the 3x3 box "
							+ "at row " + (boxRow + 1) + " col " + (boxCol + 1) + ".");
				}
			}
		}
	}
}