package com.collections.inventory.models;

public class GroceryProduct extends Product {

    private static long counter = 5000;
    private static final String PREFIX = "GR";

    private String expiryDate;

    public GroceryProduct(String name, double price, String expiryDate)
            throws InvalidProductException {

        super(name, Category.GROCERY, price, PREFIX, counter++);

        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new InvalidProductException("Expiry date can't be empty");
        }

        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() { return expiryDate; }

    @Override
    public void printDetails() {
        System.out.println(" Product ID      : " + getId());
        System.out.println(" Type            : Grocery Product");
        System.out.println(" Name            : " + getName());
        System.out.println(" Category        : " + getCategory());
        System.out.println(" Price           : Rs. " + getPrice());
        System.out.println(" Expiry Date     : " + expiryDate);
        System.out.println(" Status          : " + getStatus());
    }
}