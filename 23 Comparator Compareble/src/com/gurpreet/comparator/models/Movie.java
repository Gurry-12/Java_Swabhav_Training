package com.gurpreet.comparator.models;

public class Movie {
	private String name;
	private int year;
	
	public Movie(String name, int year) {
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name can't be empty");
		}
		
		if(year < 0 ) {
			throw new IllegalArgumentException("Year can't be negative");
		}
		this.name = name;
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
}
