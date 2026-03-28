package com.gurpreet.map;

import java.util.List;

public class ConvertNamesWithPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> names = List.of(
	            "Aarav Sharma", "Priya Singh", "Rohan Kapoor", 
	            "Ananya Verma", "Vikram Patel", "Sneha Reddy",
	            "Karan Malhotra", "Meera Iyer"
	        );
		
		names.stream().map(name -> "Mr./Ms. " + name).forEach(System.out::println);
	}

}
