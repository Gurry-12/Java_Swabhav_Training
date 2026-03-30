package com.gurpreet.model;

import com.gurpreet.enums.Category;
import com.gurpreet.enums.OrderStatus;
import com.gurpreet.exception.InvalidAmountException;
import com.gurpreet.exception.InvalidIdException;

public class Order {

	private String orderId;
	private String customerName;
	private Category category;
	private double amount;
	private OrderStatus status; // COMPLETED, PENDING, CANCELLED

	// Parameterized Constructor
	public Order(String orderId, String customerName, Category category, double amount, OrderStatus status)
			throws InvalidAmountException, InvalidIdException {

		if (orderId == null || orderId.trim().isEmpty()) {
			throw new InvalidIdException("Order ID cannot be empty.");
		}
		if (customerName == null || customerName.trim().isEmpty()) {
			throw new IllegalArgumentException("Customer name cannot be empty.");
		}
		if (category == null ) {
			throw new IllegalArgumentException("Category cannot be empty.");
		}
		if (amount <= 0) {
			throw new InvalidAmountException("Order amount must be positive.");
		}
		if (status == null ) {
			throw new IllegalArgumentException("Status cannot be empty.");
		}

		this.orderId = orderId.toUpperCase();
		this.customerName = customerName;
		this.category = category;
		this.amount = amount;
		this.status = status;
	}

	// Getters
	public String getOrderId() {
		return orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCategory() {
		return category.getDisplayName();
	}

	public double getAmount() {
		return amount;
	}

	public String getStatus() {
		return status.name();
	}

	@Override
	public String toString() {
		return String.format("Order ID: %-10s | Customer: %-18s | Category: %-12s | Amount: ₹%-8.2f | Status: %-10s",
				orderId, customerName, category, amount, status);
	}
}