package com.sudoku.models;

import java.util.Scanner;
import com.sudoku.enums.GameLevel;
import com.sudoku.exceptions.ProtectedCellException;
import com.sudoku.exceptions.SudokuException;
import com.sudoku.validators.SudokuValidator;

public class SudokuGame {

	private Scanner scanner;
	private SudokuBoard board;
	private SudokuValidator validator;

	public SudokuGame(Scanner scanner, GameLevel gameDifficulty) {
		this.board = new SudokuBoard(gameDifficulty);
		this.validator = new SudokuValidator(board);
		this.scanner = scanner;
	}

	public void start() {
		System.out.println("Welcome to Sudoku Validator!");
		board.printBoard();

		while (true) {
			System.out.println("\n1. Insert into a cell");
			System.out.println("2. Check full validation");
			System.out.println("3. Clear a cell");
			System.out.println("4. Exit");
			System.out.print("Enter choice: ");

			int userChoice = readMenuChoice();

			switch (userChoice) {

			case 1:
				handleInsert();
				board.printBoard();
				break;

			case 2:
				handleValidation();
				break;

			case 3:
				handleClear();
				board.printBoard();
				break;

			case 4:
				System.out.println("Goodbye!");
				return;

			default:
				System.out.println("Invalid choice. Enter 1, 2, 3 or 4.");
			}
		}
	}

	private void handleValidation() {
		try {
			validator.validate();
			System.out.println("Sudoku is Valid.");

		} catch (SudokuException e) {
			System.out.println("Validation Failed: " + e.getMessage());
		}
	}

	private void handleInsert() {
		try {
			System.out.print("Enter row (1-9): ");
			int row = Integer.parseInt(scanner.nextLine().trim()) - 1;

			System.out.print("Enter column (1-9): ");
			int col = Integer.parseInt(scanner.nextLine().trim()) - 1;

			System.out.print("Enter number (1-9): ");
			int inputValue = Integer.parseInt(scanner.nextLine().trim());

			if (!isValidInput(row, col, inputValue)) {
				throw new SudokuException("Row and column must be 1-9, number must be 1-9.");
			}

			if (board.isGiven(row, col)) {
				throw new ProtectedCellException(
						"Cannot overwrite pre-filled cell at row " + (row + 1) + " col " + (col + 1) + ".");
			}

			if (board.getCell(row, col) != 0) {
				throw new SudokuException("Cell at row " + (row + 1) + " col " + (col + 1) + " already has value "
						+ board.getCell(row, col) + ". Use option 3 to clear it first.");
			}

			board.setCell(row, col, inputValue);
			System.out.println("Cell updated successfully.");

			checkMoveConsequences();

		} catch (ProtectedCellException e) {
			System.out.println("Protected Cell: " + e.getMessage());

		} catch (SudokuException e) {
			System.out.println("Input Error: " + e.getMessage());

		} catch (NumberFormatException e) {
			System.out.println("Error: Please enter a valid number.");
		}
	}

	// Runs silently after insert — warns if move caused a conflict.
	private void checkMoveConsequences() {
		try {
			validator.validate();
		} catch (SudokuException e) {
			System.out.println("Warning: " + e.getMessage());
		}
	}

	private void handleClear() {
		try {
			System.out.print("Enter row to clear (1-9): ");
			int row = Integer.parseInt(scanner.nextLine().trim()) - 1;

			System.out.print("Enter column to clear (1-9): ");
			int col = Integer.parseInt(scanner.nextLine().trim()) - 1;

			if (row < 0 || row > 8 || col < 0 || col > 8) {
				throw new SudokuException("Row and column must be between 1 and 9.");
			}

			if (board.isGiven(row, col)) {
				throw new ProtectedCellException(
						"Cannot clear pre-filled cell at row " + (row + 1) + " col " + (col + 1) + ".");
			}

			if (board.getCell(row, col) == 0) {
				throw new SudokuException("Cell at row " + (row + 1) + " col " + (col + 1) + " is already empty.");
			}

			board.setCell(row, col, 0);
			System.out.println("Cell cleared.");

		} catch (ProtectedCellException e) {
			System.out.println("Protected Cell: " + e.getMessage());

		} catch (SudokuException e) {
			System.out.println("Error: " + e.getMessage());

		} catch (NumberFormatException e) {
			System.out.println("Error: Please enter a valid number.");
		}
	}

	private int readMenuChoice() {
		try {
			return Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private boolean isValidInput(int row, int col, int inputValue) {
		return (row >= 0 && row <= 8) && (col >= 0 && col <= 8) && (inputValue >= 1 && inputValue <= 9);
	}
}