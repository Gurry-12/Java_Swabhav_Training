package com.gurpreet.facade.tictactoe.model.player;

import java.util.Scanner;

import com.gurpreet.facade.tictactoe.model.Board;
import com.gurpreet.facade.tictactoe.model.enums.State;
import com.gurpreet.facade.tictactoe.model.helpers.Helpers;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String name, State state, Scanner scanner) {
        super(name, state);
        this.scanner = scanner;
    }

    @Override
    public int[] getNextMove(Board board) {
    	int size = board.getSize();
    	int lastInput = size * size;
        while (true) {
            System.out.print(name + " (" + state + "), enter from (1 - " + lastInput + ") as input: ");
            
            
            int position = Helpers.validateIntPositive(scanner);

            int row = (position - 1) / size;
            int col = (position - 1) % size;

            if (board.isValidMove(row, col)) {
                return new int[]{row, col};
            }
            System.out.println("Invalid move! Position already taken or out of bounds. Try again.");
        }
    }
}