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
    	int size = board.getSize() * board.getSize();
    	
		while (true) {
			int row = random.nextInt(size);
			int col = random.nextInt(size);
			if (board.isValidMove(row, col)) {
				return new int[] { row, col };
			}
		}
	}
}