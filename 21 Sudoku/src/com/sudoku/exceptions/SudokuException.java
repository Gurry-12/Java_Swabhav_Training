package com.sudoku.exceptions;

public class SudokuException extends Exception {

    private static final long serialVersionUID = 1L;

    public SudokuException() {
        this("Sudoku error occurred.");
    }

    public SudokuException(String message) {
        super(message);
    }
}