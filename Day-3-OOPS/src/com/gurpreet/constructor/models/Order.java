package com.gurpreet.constructor.models;

public class Order {
	private static int orderCounter = 5000;
	private final int orderId;
	private final Product product; // Order depends on Product
	private final int quantity;
	private final double totalAmount; // Calculated at creation time

	public Order(Product product, int quantity) {

		this.orderId = ++orderCounter;
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = product.getPrice() * quantity;
		
		if (quantity <= 0) {
			System.out.println("Quantity must be positive. Provided: " + quantity);
			return;
		}

		// Check stock availability
		if (quantity > product.getStockQuantity()) {
			System.out.println(
					"Insufficient stock. Requested: " + quantity + ", Available: " + product.getStockQuantity());
			return;
		}

		product.reduceStock(quantity);

		System.out.println("Order created: ID " + orderId + " | Total: ₹" + totalAmount);
	}

	// Getters only - all fields are immutable
	public int getOrderId() {
		return orderId;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void displayOrderInfo() {
		System.out.println("Order ID: " + orderId);
		System.out.println("Product: " + product.getProductName());
		System.out.println("Quantity: " + quantity);
		System.out.println("Unit Price: ₹" + product.getPrice());
		System.out.println("Total Amount: ₹" + totalAmount);
	}
}
