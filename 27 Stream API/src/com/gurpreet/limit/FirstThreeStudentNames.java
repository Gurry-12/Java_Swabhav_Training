package com.gurpreet.limit;

import java.util.List;

public class FirstThreeStudentNames {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = List.of("Amit", "Rahul", "Priya", "Suresh", "Neha", "Vikas");

        names.stream()
             .limit(3)
             .forEach(System.out::println);
	}

}
