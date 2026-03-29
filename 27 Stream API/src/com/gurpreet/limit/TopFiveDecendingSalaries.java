package com.gurpreet.limit;

import java.util.List;

public class TopFiveDecendingSalaries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> salaries = List.of(45000, 78000, 62000, 92000, 35000, 88000, 52000, 95000);

		salaries.stream().sorted((a, b) -> b - a).limit(5).forEach(System.out::println);
	}

}
