package com.gurpreet.skip;

import java.util.Comparator;
import java.util.List;

public class SortReverseAndSkipTwoSalary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> salaries = List.of(45000, 78000, 62000, 92000, 35000, 88000, 52000);

		salaries.stream()
		        .sorted(Comparator.reverseOrder())
		        .skip(2)
		        .forEach(System.out::println);
	}

}
