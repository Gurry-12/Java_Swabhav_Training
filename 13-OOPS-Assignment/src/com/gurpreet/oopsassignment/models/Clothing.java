package com.gurpreet.oopsassignment.models;

import com.gurpreet.oopsassignment.enums.Material;
import com.gurpreet.oopsassignment.enums.Sizes;

public class Clothing extends Product {
	private Sizes size;
	private Material material;

	public Clothing( String productName, double basePrice, Sizes size, Material material) {
		super( productName, basePrice);
		if (size == null) {
			throw new IllegalArgumentException("Size must not be null.");
		}
		if (material == null) {
			throw new IllegalArgumentException("Material must not be null.");
		}
		this.size = size;
		this.material = material;
	}

	public Sizes getSize() {
		return size;
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public void displayInventory() {
		System.out.println("ID: " + getProductId() + ", Name: " + getProductName() + ", Price: $" + getBasePrice()
				+ ", Size: " + size + ", Material: " + material);
	}
}