package com.ims.model;

import com.ims.model.exception.InsufficientStockException;

public class Product {

	private static int next = 1;
	private int id;
	private String name;
	private ProductType productType;
	private int threshold;
	private int stock;
	private double price;

	public Product(String name, ProductType productType, int threshold, int stock, double price) {
		this.id = next++;
		this.name = name;
		this.productType = productType;
		this.threshold = threshold;
		this.price = price;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public int getThreshold() {
		return threshold;
	}

	public int getStock() {
		return stock;
	}

	public double getPrice() {
		return price;
	}

	public void addStock(int quantity) {
		stock += quantity;
	}

	public void removeStock(int quantity) throws InsufficientStockException {
		if (quantity > stock) {
			throw new InsufficientStockException(quantity, stock);
		}
		stock -= quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format(
				"Id : %-2d | Name : %-15s | Type : %-12s | Threshold : %-4d | Stock : %-4d | Price : %-6.2f", id, name,
				productType.toString(), threshold, stock, price);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof Product other))
			return false;
		return this.id == other.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}