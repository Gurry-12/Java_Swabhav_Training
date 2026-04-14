package com.gurpreet.facade.tictactoe.model.helpers;

import com.gurpreet.facade.tictactoe.model.Board;
import com.gurpreet.facade.tictactoe.model.enums.State;

public class TicTacToeUtil {

	private static final int DASH_LENGTH = 4;
	private static final int ONE = 1;

	public static void display(Board board) {
		System.out.println();
		int size = board.getSize();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + getSymbol(board.getCell(i, j).getState()) + " ");
				if (j < size - ONE) 
					System.out.print("|");
			}
			System.out.println();
			if (i < size - ONE) { 
				System.out.println("-".repeat(size * DASH_LENGTH - ONE));
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