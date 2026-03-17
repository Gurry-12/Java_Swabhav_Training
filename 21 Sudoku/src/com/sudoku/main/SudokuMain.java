package com.sudoku.main;

import java.util.Scanner;

import com.sudoku.models.SudokuGame;

public class SudokuMain {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		SudokuGame game = new SudokuGame(scanner);
		
		game.start();
		
		scanner.close();
		
	}
}
