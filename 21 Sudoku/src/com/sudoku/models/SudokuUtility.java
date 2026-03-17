package com.sudoku.models;

import java.util.Random;

public class SudokuUtility {

	public static boolean isSafe(int[][] grid, int row, int col, int val) {

		// row
		for (int c = 0; c < 9; c++) {
			if (grid[row][c] == val)
				return false;
		}

		// column
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

	public static boolean isValidInput(int row, int col, int val) {
		return (row >= 0 && row <= 8) && (col >= 0 && col <= 8) && (val >= 1 && val <= 9);

	}

	public static void printBoard(SudokuBoard board) {
		int[][] grid = board.getGrid();
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

				if (grid[r][c] == 0)
					System.out.print(".");
				else
					System.out.print(grid[r][c]);

				System.out.print(" ");
			}
			System.out.println("|");
		}
		System.out.println("+--------+--------+-------+");
	}

	public static void shuffleArray(int[] arr) {
		Random random = new Random();

		for (int i = arr.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);

			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
}
