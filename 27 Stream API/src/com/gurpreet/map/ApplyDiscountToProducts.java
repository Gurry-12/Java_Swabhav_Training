package com.gurpreet.map;

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

public class ApplyDiscountToProducts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Product> products = List.of(new Product("Wireless Headphones", 1299), new Product("Notebook", 250),
				new Product("Smartphone", 24999), new Product("Water Bottle", 450), new Product("Laptop Bag", 899),
				new Product("Office Chair", 5999), new Product("USB Cable", 299), new Product("Monitor", 12499),
				new Product("Pen Drive", 650), new Product("Backpack", 1499));

		List<Double> newPricesOfProducts = products.stream()
				.map(product -> (product.getPrice() - product.getPrice() * 0.1)).toList();
		
		newPricesOfProducts.forEach(System.out::println);;

	}

}
