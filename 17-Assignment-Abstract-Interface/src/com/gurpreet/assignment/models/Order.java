package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidOrderException;
import com.gurpreet.assignment.interfaces.Verifiable;

public abstract class Order implements Verifiable {
    protected String orderId;
    protected String customerName;
    protected double orderAmount;
    private static int orderCounter = 1;

    protected static String warehouseLocation;
    protected static double expressFee;
    protected static double internationalTaxRate;

    static {
        System.out.println("[System] Loading configuration...");
        warehouseLocation = "Jaipur Distribution Center";
        expressFee = 180.00;
        internationalTaxRate = 0.22; // 22%
    }

    public Order( String customerName, double orderAmount)
            throws InvalidOrderException {
        
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidOrderException("Customer name is required.");
        }
        if (orderAmount <= 0) {
            throw new InvalidOrderException("Order amount must be positive.");
        }

        this.orderId = "ORD" + String.format("%03d", orderCounter++);
        this.customerName = customerName.trim();
        this.orderAmount = orderAmount;
    }

    public void displayOrderSummary() {
        System.out.printf("  %-10s  %-20s  ₹%10.2f%n",
                orderId, customerName, orderAmount);
    }

    public abstract void processOrder();

    @Override
    public boolean verifyOrder() throws InvalidOrderException {
        if (orderAmount <= 0) {
            throw new InvalidOrderException("Invalid amount during verification.");
        }
        return true;
    }
    
    public String getOrderId() {
    	return orderId;
    }
}