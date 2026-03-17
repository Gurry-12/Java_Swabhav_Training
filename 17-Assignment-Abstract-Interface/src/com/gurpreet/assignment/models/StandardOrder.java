package com.gurpreet.assignment.models;

import com.gurpreet.assignment.exceptions.InvalidOrderException;

public class StandardOrder extends Order {
    public StandardOrder(String name, double amount) throws InvalidOrderException {
        super(name, amount);
    }

    @Override
    public void processOrder() {
        System.out.println("  Processing: Standard Order");
        System.out.println("  • Warehouse     : " + warehouseLocation);
        System.out.println("  • Delivery time : 4–7 business days");
        System.out.println("  • Extra charges : None");
        System.out.println("  Status: Completed");
    }
}

