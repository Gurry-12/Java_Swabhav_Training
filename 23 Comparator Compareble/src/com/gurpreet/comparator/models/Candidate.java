package com.gurpreet.comparator.models;

public class Candidate {

	private String name;
	private int age;
	
	public Candidate(String name, int age) {
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name can not be null or empty.");
		}
		
		if(age < 0 ) {
			throw new IllegalArgumentException("Age can not be negative");
		}
		
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
}
