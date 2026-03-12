package com.gurpreet.encapsulation.models;

public class Product {
	private long counter = 10000l;
    private String productId ;
    private String productName;
    private double price;
    private int stockQuantity;
    private boolean discontinued = false;

    // Constructor with validation
    public Product( String productName, double price, int stockQuantity) {
		if (price < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }
        if (stockQuantity < 0) {
            System.out.println("Stock quantity cannot be negative.");
            return;
        }
        this.productId = Long.toString(++counter);
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getter methods for read-only access
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    // Method to increase stock with validation
    public void increaseStock(int amount) {
        if (discontinued) {
            System.out.println("Cannot modify stock for discontinued product.");
            return;
        }
        if (amount < 0) {
            System.out.println("Stock increase amount cannot be negative.");
            return;
        }
        stockQuantity += amount;
    }

    // Method to reduce stock with validation
    public void reduceStock(int amount) {
        if (discontinued) {
            System.out.println("Cannot modify stock for discontinued product.");
            return;
        }
        if (amount < 0) {
            System.out.println("Stock reduction amount cannot be negative.");
            return;
        }
        if (amount > stockQuantity) {
            System.out.println("Cannot reduce stock below zero.");
            return;
        }
        stockQuantity -= amount;
    }


    public void updatePrice(double newPrice) {
        if (discontinued) {
            System.out.println("Cannot update price for discontinued product.");
            return;
        }
        if (newPrice < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }
        price = newPrice;
    }

    // Method to discontinue the product
    public void discontinueProduct() {
        discontinued = true;
    }

    // Setter for productName (controlled, if needed)
    public void setProductName(String productName) {
        if (discontinued) {
            System.out.println("Cannot modify discontinued product.");
            return;
        }
        this.productName = productName;
    }
}