package com.gurpreet.facade.tictactoe.model.enums;

public enum BoardSize {
	THREE(3),
	FOUR(4),
	FIVE(5);

	private int size;

	BoardSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

}
