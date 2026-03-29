package com.gurpreet.skip;

import java.util.List;

public class SkipFirstThreeNames {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = List.of("Anuj", "Rahul", "Priya", "Suresh", "Neha", "Vikas", "Anjali");
		
		names.stream().skip(3).forEach(System.out::println);
	}

}
