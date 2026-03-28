package com.sudoku.exceptions;

public class ProtectedCellException extends SudokuException {

    private static final long serialVersionUID = 1L;

    public ProtectedCellException(String message) {
        super(message);
    }
}