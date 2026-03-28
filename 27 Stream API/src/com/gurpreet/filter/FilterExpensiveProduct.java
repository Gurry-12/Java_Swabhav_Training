package com.gurpreet.filter;

import java.util.List;

class Product {
	private final String name;
	private final double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return name + " - " + price;
	}
}

public class FilterExpensiveProduct {

	public static void main(String[] args) {

		List<Product> products = List.of(new Product("Wireless Headphones", 1299), new Product("Notebook", 250),
				new Product("Smartphone", 24999), new Product("Water Bottle", 450), new Product("Laptop Bag", 899),
				new Product("Office Chair", 5999), new Product("USB Cable", 299), new Product("Monitor", 12499),
				new Product("Pen Drive", 650), new Product("Backpack", 1499));

		
		System.out.println("Products costing more than ₹500:\n");

		products.stream()
                .filter(product -> product.getPrice() > 500)
                .forEach(System.out::println);        

	}
}