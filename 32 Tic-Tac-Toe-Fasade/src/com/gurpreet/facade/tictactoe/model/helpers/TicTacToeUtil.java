package com.gurpreet.facade.tictactoe.model.helpers;

import com.gurpreet.facade.tictactoe.model.Board;
import com.gurpreet.facade.tictactoe.model.enums.State;

public class TicTacToeUtil {
	
	 public static void display(Board board) {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                System.out.print(" " + getSymbol(board.getCell(i, j).getState()) + " ");
	                if (j < 2)
	                    System.out.print("|");
	            }
	            System.out.println();
	            if (i < 2) {
	                System.out.println("-".repeat(3 * 4 - 1));
	            }
	        }
	    }

	    private static String getSymbol(State state) {
	        return switch (state) {
	            case X -> "X";
	            case O -> "O";
	            case EMPTY -> " ";
	        };
	    }
}
