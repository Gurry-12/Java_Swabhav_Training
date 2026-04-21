package com.gurpreet.model;

public class ArrayReverse {

	public int[] reverseArray(int[] input) {
		if (input == null)
			return null;

		int[] reversed = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			reversed[i] = input[input.length - 1 - i];
		}
		return reversed;
	}

}
