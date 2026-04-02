package com.gurpreet.miniproject.tictactoe.models;

import com.gurpreet.miniproject.tictactoe.enums.State;
import com.gurpreet.miniproject.tictactoe.helpers.Helpers;

import java.util.Scanner;

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
            scanner.nextLine(); // consume newline

            int row = (position - 1) / 3;
            int col = (position - 1) % 3;

            if (board.isValidMove(row, col)) {
                return new int[]{row, col};
            }
            System.out.println("Invalid move! Position already taken or out of bounds. Try again.");
        }
    }
}