package com.gurpreet.oopsassignment.models;

public abstract class Product {
	private String productId;
	private String productName;
	private double basePrice;
	private static long productCounter = 0;
	private final static String prefix = "S";
	public Product(String productName, double basePrice) {
		
		if (productName == null || productName.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name must not be null or empty.");
		}
		if (basePrice < 0) {
			throw new IllegalArgumentException("Base price cannot be negative.");
		}
		this.productId = prefix + String.format("%04d", ++productCounter);
		this.productName = productName;
		this.basePrice = basePrice;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative.");
		}
		this.basePrice = price;
	}

	public abstract void displayInventory();
}

