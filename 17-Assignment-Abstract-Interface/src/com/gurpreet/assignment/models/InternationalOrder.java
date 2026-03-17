package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidOrderException;

public class InternationalOrder extends Order {
    public InternationalOrder( String name, double amount) throws InvalidOrderException {
        super(name, amount);
    }

    @Override
    public boolean verifyOrder() throws InvalidOrderException {
        if (orderAmount < 8000) {
            throw new InvalidOrderException(
                "International orders must be at least ₹8,000. Current: " + orderAmount);
        }
        return super.verifyOrder();
    }

    @Override
    public void processOrder() {
        double tax = orderAmount * internationalTaxRate;
        double finalAmount = orderAmount + tax;
        System.out.println("  Processing: International Order");
        System.out.println("  • Warehouse     : " + warehouseLocation);
        System.out.printf ("  • Tax (%.0f%%)   : ₹%.2f%n", internationalTaxRate * 100, tax);
        System.out.printf ("  • Final amount  : ₹%.2f%n", finalAmount);
        System.out.println("  • Delivery time : 8–15 business days");
        System.out.println("  Status: Completed");
    }
}