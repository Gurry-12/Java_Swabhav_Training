package com.gurpreet.constructor.models;

/**
 * Question 3: E-Commerce Product & Order System
 * 
 * Product class with immutable ID and validated price
 */
public class Product {
    private static int productCounter = 1000;
    private final int productId;
    private final String productName;
    
    private double price;
    private int stockQuantity;
    
    
    public Product(String productName, double price, int stockQuantity) {
    	this.productId = ++productCounter;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Product name cannot be null or empty");
            return;
        }
        
        if (price <= 0) {
            System.out.println("Price must be positive. Provided: " + price);
            return;
        }
        
        if (stockQuantity < 0) {
            System.out.println("Stock quantity cannot be negative");
            return;
        }
        
        System.out.println("Product created: " + productName + " (ID: " + productId + ")");
    }
    
   
    public Product(String productName, double price) {
        this(productName, price, 0); // Default stock = 0
    }
    
    // Getters
    public int getProductId() {
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
    
    // Controlled setter for price
    public void updatePrice(double newPrice) {
        if (newPrice <= 0) {
            System.out.println("Price must be positive");
            return;
        }
        this.price = newPrice;
        System.out.println("Price updated to: ₹" + newPrice);
    }
    
    // Controlled methods for stock
    public void addStock(int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return;
        }
        this.stockQuantity += quantity;
    }
    
    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return;
        }
        if (quantity > stockQuantity) {
            System.out.println("Insufficient stock. Available: " + stockQuantity);
            return;
        }
        this.stockQuantity -= quantity;
    }
    
    public void displayProductInfo() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + productName);
        System.out.println("Price: ₹" + price);
        System.out.println("Stock: " + stockQuantity);
    }
}
