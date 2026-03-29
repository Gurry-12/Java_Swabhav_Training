package com.gurpreet.findfirst;

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

public class FindFirstExpensiveProduct {
	public static void main(String[] args) {
		List<Product> products = List.of(new Product("Phone", 42000), new Product("Laptop", 65000),
				new Product("Tablet", 28000), new Product("Camera", 55000));

		Product firstExpensive = products.stream().filter(product -> product.getPrice() > 500).findFirst().orElse(null);
		System.out.println(firstExpensive);
		
	}
}
