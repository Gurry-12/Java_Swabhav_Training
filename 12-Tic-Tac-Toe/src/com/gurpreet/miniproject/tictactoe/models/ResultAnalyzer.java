package com.gurpreet.miniproject.tictactoe.models;

import com.gurpreet.miniproject.tictactoe.enums.State;

public class ResultAnalyzer {
    private final Board board;

    public ResultAnalyzer(Board board) {
        this.board = board;
    }

    public String analyze() {
        // Rows
        for (int i = 0; i < 3; i++) {
            if (isSameState(i, 0, i, 1, i, 2)) {
                return board.getCell(i, 0).getState() + " WINS";
            }
        }
        // Columns
        for (int j = 0; j < 3; j++) {
            if (isSameState(0, j, 1, j, 2, j)) {
                return board.getCell(0, j).getState() + " WINS";
            }
        }
        // Diagonals
        if (isSameState(0, 0, 1, 1, 2, 2)) {
            return board.getCell(0, 0).getState() + " WINS";
        }
        if (isSameState(0, 2, 1, 1, 2, 0)) {
            return board.getCell(0, 2).getState() + " WINS";
        }

        return board.isFull() ? "DRAW" : "CONTINUE";
    }

    private boolean isSameState(int r1, int c1, int r2, int c2, int r3, int c3) {
        State s1 = board.getCell(r1, c1).getState();
        State s2 = board.getCell(r2, c2).getState();
        State s3 = board.getCell(r3, c3).getState();
        return s1 != State.EMPTY && s1 == s2 && s2 == s3;
    }
}