package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidOrderException;

public class ExpressOrder extends Order {
    public ExpressOrder(String name, double amount) throws InvalidOrderException {
        super(name, amount);
    }

    @Override
    public void processOrder() {
        double finalAmount = orderAmount + expressFee;
        System.out.println("  Processing: Express Order");
        System.out.println("  • Warehouse     : " + warehouseLocation);
        System.out.printf ("  • Express fee   : ₹%.2f%n", expressFee);
        System.out.println("  • Delivery time : 1–2 business days");
        System.out.printf ("  • Final amount  : ₹%.2f%n", finalAmount);
        System.out.println("  Status: Completed");
    }
}