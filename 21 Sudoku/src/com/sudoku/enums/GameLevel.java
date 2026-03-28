package com.sudoku.enums;

public enum GameLevel {

    EASY(55), MEDIUM(45), HARD(35), EMPTY(0);

    private int filledBoxes;

    GameLevel(int filledBoxes) {
        this.filledBoxes = filledBoxes;
    }

    public int getFilledBoxes() {
        return filledBoxes;
    }
}