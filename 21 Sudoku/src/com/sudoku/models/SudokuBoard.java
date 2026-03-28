package com.sudoku.models;

import java.util.Random;
import com.sudoku.enums.GameLevel;

public class SudokuBoard {

	private int[][] grid;
	private boolean[][] isGiven;

	public SudokuBoard() {
		this(GameLevel.EMPTY);
	}

	public SudokuBoard(GameLevel gameDifficulty) {
		if (gameDifficulty == null) {
			throw new IllegalArgumentException("Game difficulty cannot be null.");
		}
		this.grid = new int[9][9];
		this.isGiven = new boolean[9][9];
		fillBoard(gameDifficulty);
	}

	// NOTE: This is not a full Sudoku puzzle generator.
	// It places numbers randomly using safe placement rules but does NOT
	// guarantee a uniquely solvable puzzle.
	private void fillBoard(GameLevel gameDifficulty) {
		Random random = new Random();
		int filled = 0;
		int attempts = 0;
		int maxAttempts = 1000;

		while (filled < gameDifficulty.getFilledBoxes() && attempts < maxAttempts) {

			int randomRow = random.nextInt(9);
			int randomCol = random.nextInt(9);

			if (grid[randomRow][randomCol] != 0) {
				attempts++;
				continue;
			}

			int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			shuffleArray(nums);

			boolean placed = false;

			for (int candidate : nums) {
				if (isSafe(grid, randomRow, randomCol, candidate)) {
					grid[randomRow][randomCol] = candidate;
					isGiven[randomRow][randomCol] = true;
					filled++;
					placed = true;
					break;
				}
			}

			if (!placed)
				attempts++;
		}
	}

	public int getCell(int row, int col) {
		return grid[row][col];
	}

	public void setCell(int row, int col, int value) {
		if (row < 0 || row > 8 || col < 0 || col > 8) {
			throw new IllegalArgumentException("Row and column must be between 0 and 8.");
		}
		grid[row][col] = value;
	}

	public boolean isGiven(int row, int col) {
		return isGiven[row][col];
	}

	public int[][] getGrid() {
		int[][] copy = new int[9][9];
		for (int r = 0; r < 9; r++) {
			copy[r] = grid[r].clone();
		}
		return copy;
	}

	public void printBoard() {
		int[][] currentGrid = getGrid();
		System.out.println("+--------+--------+-------+");

		for (int r = 0; r < 9; r++) {
			if (r == 3 || r == 6) {
				System.out.println("+--------+--------+-------+");
			}

			for (int c = 0; c < 9; c++) {
				if (c == 0)
					System.out.print("| ");
				else if (c == 3 || c == 6)
					System.out.print(" | ");

				System.out.print(currentGrid[r][c] == 0 ? "." : currentGrid[r][c]);
				System.out.print(" ");
			}
			System.out.println("|");
		}
		System.out.println("+--------+--------+-------+");
	}

	private void shuffleArray(int[] arr) {
		Random random = new Random();
		for (int i = arr.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	private boolean isSafe(int[][] grid, int row, int col, int val) {
		for (int c = 0; c < 9; c++) {
			if (grid[row][c] == val)
				return false;
		}

		for (int r = 0; r < 9; r++) {
			if (grid[r][col] == val)
				return false;
		}

		int boxRow = (row / 3) * 3;
		int boxCol = (col / 3) * 3;

		for (int r = boxRow; r < boxRow + 3; r++) {
			for (int c = boxCol; c < boxCol + 3; c++) {
				if (grid[r][c] == val)
					return false;
			}
		}

		return true;
	}
}