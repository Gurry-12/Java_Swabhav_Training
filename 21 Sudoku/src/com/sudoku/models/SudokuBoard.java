package com.sudoku.models;

import java.util.Random;

public class SudokuBoard {

	private int[][] grid;
	private boolean[][] isGiven;
	
	public SudokuBoard() {
		this.grid = new int[9][9];
		this.isGiven = new boolean[9][9];
		fillBoard();
	}
	
	public void fillBoard() {
		Random random = new Random();
		int filled = 0;
		int attempts = 0;
		
		while(filled < 45 && attempts < 1000) {
			
			int randomRow = random.nextInt(0,9);
			int randomCol = random.nextInt(0,9);
			
			if(grid[randomRow][randomCol] != 0)
			{
				attempts++;
				continue;
			}
			
			int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            SudokuUtility.shuffleArray(nums);
            
            boolean placed = false;

            for (int n : nums) {
                if (SudokuUtility.isSafe(grid, randomRow, randomCol, n)) {
                    grid[randomRow][randomCol]    = n;
                    isGiven[randomRow][randomCol] = true;
                    filled++;
                    placed = true;
                    break;
                }
            }

            if (!placed) attempts++;
		}
	}
	
	public int getCell(int row, int col) {
		return grid[row][col];
	}
    
	public void setCell(int row, int col, int value) {
		grid[row][col] = value;
	}
    
	public boolean isGiven(int row, int col) {
		return isGiven[row][col];
	}
    
	public int[][] getGrid() {
		return grid;
	}
}
