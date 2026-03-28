package com.gurpreet.sorted;

import java.util.List;

public class SortNumbersInIncreasingOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = List.of(2, 5, 8, 10, 15, 20, 25, 30, 1 , 5 , 7 , 4, 87);
		
		numbers.stream().sorted().forEach(System.out::println);
	}

}
