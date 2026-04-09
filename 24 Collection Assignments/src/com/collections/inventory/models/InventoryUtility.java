package com.collections.inventory.models;

public class InventoryUtility {

    public static void displayProductTypes() {
        System.out.println("Select Product Type:");
        System.out.println("1. Electronic Product");
        System.out.println("2. Grocery Product");
    }

    public static void displayMenu() {
        System.out.println("\n====== Product Inventory System ======");
        System.out.println("1. Add Product");
        System.out.println("2. View Product by ID");
        System.out.println("3. View All Products (sorted by name)");
        System.out.println("4. View All Products (sorted by category & price)");
        System.out.println("5. Request Product Return");   // queue a return
        System.out.println("6. Process Next Return Request"); // process from queue
        System.out.println("7. View Products by Category");
        System.out.println("8. Remove Product");
        System.out.println("9. Exit");
        System.out.println("======================================");
    }
}