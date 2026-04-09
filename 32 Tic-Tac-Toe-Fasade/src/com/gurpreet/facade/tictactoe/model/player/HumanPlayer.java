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
        while (true) {
            System.out.print(name + " (" + state + "), enter from (1-9) as input: ");
            
            
            int position = Helpers.validateInt(scanner);

            int row = (position - 1) / 3;
            int col = (position - 1) % 3;

            if (board.isValidMove(row, col)) {
                return new int[]{row, col};
            }
            System.out.println("Invalid move! Position already taken or out of bounds. Try again.");
        }
    }
}