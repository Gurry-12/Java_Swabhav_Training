package com.gurpreet.minmax;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;



public class SmallesNumberOfList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> numbers = List.of(2, 5, 8, 10, 15, 20, 25, 30, 1 , 5 , 7 , 4, 87 , -33);
		
		Integer min = numbers.stream()
			    .min(Comparator.naturalOrder()).orElse(null);
		
		System.out.println(min);
	}

}
