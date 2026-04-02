package com.gurpreet.miniproject.tictactoe.models;

import com.gurpreet.miniproject.tictactoe.enums.State;

public abstract class Player {
    protected final String name;
    protected final State state;

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public abstract int[] getNextMove(Board board);

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }
}