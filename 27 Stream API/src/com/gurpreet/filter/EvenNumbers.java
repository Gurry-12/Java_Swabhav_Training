package com.gurpreet.filter;

import java.util.List;

public class EvenNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> numbers = List.of(3 , 2 , 5, 7, 44, 35, 13, 78);
		
		List<Integer> even = numbers.stream().filter(number -> number % 2 == 0).toList();
		
		System.out.println("Even numbers of list : ");
		for(Integer number : even) {
			System.out.println(number);
		}
	}

}
