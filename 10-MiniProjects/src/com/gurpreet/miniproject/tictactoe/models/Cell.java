package com.gurpreet.miniproject.tictactoe.models;

import com.gurpreet.miniproject.tictactoe.enums.State;

public class Cell {
    private State state = State.EMPTY;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (this.state == State.EMPTY) {
            this.state = state;
        }
    }

    public boolean isEmpty() {
        return state == State.EMPTY;
    }
}