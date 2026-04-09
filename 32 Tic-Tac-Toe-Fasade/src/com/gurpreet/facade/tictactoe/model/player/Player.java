package com.gurpreet.facade.tictactoe.model.player;

import com.gurpreet.facade.tictactoe.model.Board;
import com.gurpreet.facade.tictactoe.model.enums.State;

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