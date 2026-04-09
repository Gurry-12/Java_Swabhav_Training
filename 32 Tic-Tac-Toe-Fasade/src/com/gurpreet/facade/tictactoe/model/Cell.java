package com.gurpreet.facade.tictactoe.model;

import com.gurpreet.facade.tictactoe.model.enums.State;

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