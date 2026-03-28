package com.gurpreet.count;

import java.util.List;

public class CountTotalEvenOddNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = List.of(2, 5, 8, 10, 15, 20, 25, 30, 1 , 5 , 7 , 4, 87);
		
		long countOfEvenNumbers = numbers.stream().filter(number -> number % 2 == 0 ).count();
		
		long countOfOddNumbers = numbers.stream().filter(number -> number % 2 != 0 ).count();
		
		System.out.println("Odd Numbers Count : " + countOfOddNumbers);
		System.out.println("Even Numbers Count : " + countOfEvenNumbers);
	}

}
