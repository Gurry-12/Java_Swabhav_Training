package com.gurpreet.comparator.models;

public class Product {

	private String category;
	private String name;
	private double price;
	
	public Product(String category, String name, double price) {
		
		if(category == null || category.trim().isEmpty()) {
			throw new IllegalArgumentException("Category can't be empty");
		}
		
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name can't be empty");
		}
		
		if(price < 0) {
			throw new IllegalArgumentException("Price can't be negative");
		}
		
		this.category = category;
		this.name = name;
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
	    return category + " " + name + " " + price;
	}
}
