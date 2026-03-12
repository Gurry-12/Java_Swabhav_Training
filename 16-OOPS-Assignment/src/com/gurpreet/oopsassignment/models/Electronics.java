package com.gurpreet.oopsassignment.models;

public class Electronics extends Product {
	private int warrantyYears; // Corrected spelling
	private String brand;

	public Electronics(String productName, double basePrice, int warrantyYears, String brand) {
		super(productName, basePrice);
		if (warrantyYears < 0) {
			throw new IllegalArgumentException("Warranty years must be non-negative.");
		}
		if (brand == null || brand.trim().isEmpty()) {
			throw new IllegalArgumentException("Brand must not be null or empty.");
		}
		this.warrantyYears = warrantyYears;
		this.brand = brand;
	}

	public int getWarrantyYears() {
		return warrantyYears;
	}

	public String getBrand() {
		return brand;
	}

	@Override
	public void displayInventory() {
		System.out.println("ID: " + getProductId() + ", Name: " + getProductName() + ", Price: $" + getBasePrice()
				+ ", Warranty: " + warrantyYears + " years, Brand: " + brand);
	}
}
