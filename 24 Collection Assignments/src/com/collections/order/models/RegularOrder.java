package com.collections.order.models;

public class RegularOrder extends Order {

	private static long counter = 1000;
	private static final String PREFIX = "RO";

	private int deliveryDays;

	public RegularOrder(String customerName, String itemDescription, double totalAmount, int deliveryDays)
			throws InvalidOrderException {

		super(customerName, itemDescription, totalAmount, OrderStatus.PENDING, PREFIX, counter++);

		if (deliveryDays <= 0) {
			throw new InvalidOrderException("Delivery days must be greater than 0");
		}

		this.deliveryDays = deliveryDays;
	}

	public int getDeliveryDays() {
		return deliveryDays;
	}

	@Override
	public void printDetails() {
		System.out.println(" Order ID        : " + getId());
		System.out.println(" Type            : Regular Order");
		System.out.println(" Customer Name   : " + getCustomerName());
		System.out.println(" Item            : " + getItemDescription());
		System.out.println(" Total Amount    : Rs. " + getTotalAmount());
		System.out.println(" Delivery Days   : " + deliveryDays + " days");
		System.out.println(" Status          : " + getStatus());
	}
}