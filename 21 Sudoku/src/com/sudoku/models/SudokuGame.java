package com.sudoku.models;

import java.util.Scanner;

import com.sudoku.exceptions.InvalidGridException;

public class SudokuGame {
	
	private Scanner scanner;
	private SudokuBoard board;
	private SudokuValidator validator;

	public SudokuGame(Scanner scanner) {
		
		this.board = new SudokuBoard();
		this.validator = new SudokuValidator(board);
		this.scanner = scanner;
	}

	

	public void start() {

        System.out.println("Welcome to Sudoku Validator!");
        SudokuUtility.printBoard(board);

        while (true) {

            System.out.println("\n1. Insert into a cell");
            System.out.println("2. Check validation");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String input  = scanner.nextLine().trim();

            switch (input) {

                case "1":
                    handleInsert();
                    SudokuUtility.printBoard(board);
                    break;

                case "2":
                    boolean valid = validator.validate();
                    System.out.println(validator.getResult());
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    return;                       

                default:
                    System.out.println("Invalid choice. Enter 1, 2 or 3.");
            }
        }
    }

    // ─── handleInsert ─────────────────────────────────────
    private void handleInsert() {

        try {

            System.out.print("Enter row (1-9): ");
            int row = Integer.parseInt(scanner.nextLine().trim()) - 1;

            System.out.print("Enter column (1-9): ");
            int col = Integer.parseInt(scanner.nextLine().trim()) - 1;

            System.out.print("Enter number (1-9): ");
            int val = Integer.parseInt(scanner.nextLine().trim());

            // check 1 — range
            if (!SudokuUtility.isValidInput(row, col, val)) {
                throw new InvalidGridException(
                    "Invalid input. Row and column must be 1-9, number must be 1-9."
                );
            }

            if (board.isGiven(row, col)) {
                throw new InvalidGridException(
                    "Cannot overwrite a given cell at row "
                    + (row + 1) + " col " + (col + 1) + "."
                );
            }

            board.setCell(row, col, val);
            System.out.println("Cell updated successfully.");

        } catch (InvalidGridException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }
    }
}