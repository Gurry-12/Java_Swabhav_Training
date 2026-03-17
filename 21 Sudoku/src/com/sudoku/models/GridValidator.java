package com.sudoku.models;

public abstract class GridValidator {

	protected SudokuBoard board;
	protected String result;

	public GridValidator(SudokuBoard board) {
		this.board = board;
		this.result = "";
	}

	public abstract boolean validate();

	public String getResult() {
		return result;
	}

	public SudokuBoard getBoard() {
		return board;
	}

	protected boolean hasDuplicate(int[] arr) {
		boolean[] seen = new boolean[10];

		for (int num : arr) {
			if (num == 0)
				continue;
			if (seen[num])
				return true;
			seen[num] = true;
		}
		return false;
	}
}
