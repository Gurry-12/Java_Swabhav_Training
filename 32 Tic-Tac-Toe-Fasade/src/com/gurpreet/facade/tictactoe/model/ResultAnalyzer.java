package com.gurpreet.facade.tictactoe.model;

import com.gurpreet.facade.tictactoe.model.enums.GameStatus;
import com.gurpreet.facade.tictactoe.model.enums.State;

public class ResultAnalyzer {

    private GameStatus status = GameStatus.ONGOING;

    public ResultAnalyzer() {
        
    }

    public void checkWinner(Board board) {
        // Rows
        for (int i = 0; i < 3; i++) {
            if (isSameState(board, i, 0, i, 1, i, 2)) {
                status = GameStatus.WINNER;
                return;
            }
        }
        // Columns
        for (int j = 0; j < 3; j++) {
            if (isSameState(board, 0, j, 1, j, 2, j)) {
                status = GameStatus.WINNER;
                return;
            }
        }
        // Diagonals
        if (isSameState(board, 0, 0, 1, 1, 2, 2)) {
            status = GameStatus.WINNER;
            return;
        }
        if (isSameState(board, 0, 2, 1, 1, 2, 0)) {
            status = GameStatus.WINNER;
            return;
        }

        if (board.isFull()) {
            status = GameStatus.DRAW;
        } else {
            status = GameStatus.ONGOING;
        }
    }

    public GameStatus getStatus() {
        return status;
    }

    private boolean isSameState(Board board, int r1, int c1, int r2, int c2, int r3, int c3) {
        State s1 = board.getCell(r1, c1).getState();
        State s2 = board.getCell(r2, c2).getState();
        State s3 = board.getCell(r3, c3).getState();
        return s1 != State.EMPTY && s1 == s2 && s2 == s3;
    }
}