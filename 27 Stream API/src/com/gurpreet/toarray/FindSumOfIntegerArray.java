package com.gurpreet.toarray;

import java.util.Arrays;

public class FindSumOfIntegerArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] numbers = {2, 5, 8, 10, 15, 20, 25, 30, 1 , 5 , 7 , 4, 87 };
		
		int sumOfIntegers = Arrays.stream(numbers).sum();
		
		System.out.println(sumOfIntegers);
	}

}
