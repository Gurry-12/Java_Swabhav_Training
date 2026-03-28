package com.collections.inventory.models;

import com.collections.inventory.abstracts.Product;
import com.collections.inventory.enums.Category;
import com.collections.inventory.exceptions.InvalidProductException;

public class ElectronicProduct extends Product {

    private static long counter = 1000;
    private static final String PREFIX = "EP";

    private int warrantyMonths;

    public ElectronicProduct(String name, double price, int warrantyMonths)
            throws InvalidProductException {

        super(name, Category.ELECTRONICS, price, PREFIX, counter++);

        if (warrantyMonths < 0) {
            throw new InvalidProductException("Warranty months can't be negative");
        }

        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void printDetails() {
        System.out.println(" Product ID      : " + getId());
        System.out.println(" Type            : Electronic Product");
        System.out.println(" Name            : " + getName());
        System.out.println(" Category        : " + getCategory());
        System.out.println(" Price           : Rs. " + getPrice());
        System.out.println(" Warranty        : " + warrantyMonths + " months");
        System.out.println(" Status          : " + getStatus());
    }
}