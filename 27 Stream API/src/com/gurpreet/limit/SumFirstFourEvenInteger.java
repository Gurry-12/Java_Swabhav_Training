package com.gurpreet.limit;

import java.util.List;

public class SumFirstFourEvenInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = List.of(12, 45, 67, 34, 89, 56, 90, 78, 11, 22);
        int sum = numbers.stream()
                         .filter(n -> n % 2 == 0)
                         .limit(4)
                         .mapToInt(Integer::intValue)
                         .sum();
        
        System.out.println(sum);
	}

}
