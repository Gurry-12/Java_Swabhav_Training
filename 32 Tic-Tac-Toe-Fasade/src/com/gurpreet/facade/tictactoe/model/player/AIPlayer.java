package com.gurpreet.facade.tictactoe.model.player;

import java.util.Random;

import com.gurpreet.facade.tictactoe.model.Board;
import com.gurpreet.facade.tictactoe.model.enums.State;

public class AIPlayer extends Player {
	private final Random random = new Random();

	public AIPlayer(String name, State state) {
		super(name, state);
	}

	@Override
	public int[] getNextMove(Board board) {
		while (true) {
			int row = random.nextInt(3);
			int col = random.nextInt(3);
			if (board.isValidMove(row, col)) {
				return new int[] { row, col };
			}
		}
	}
}