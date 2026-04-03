package com.ims.model.inventorymodel;

import java.util.List;

import com.ims.model.Product;

public class MenuRenderer {

    public void showHeader() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("           Inventory Management System                     ");
        System.out.println("-----------------------------------------------------------\n");
    }

    public void showMenu() {
        System.out.println("\n--- Menu --- ");
        System.out.println("1. Add Product");
        System.out.println("2. Add Stock to Existing Product");
        System.out.println("3. Remove Stock");
        System.out.println("4. Calculate Valuation");
        System.out.println("5. List Products");
        System.out.println("6. Switch Valuation Strategy");
        System.out.println("7. Exit");
        System.out.print("Choice: ");
    }

    public void showStrategyMenu() {
        System.out.println("\nSelect Valuation Strategy:");
        System.out.println("1. FIFO (First In First Out)");
        System.out.println("2. LIFO (Last In First Out)");
        System.out.print("Choice: ");
    }

    public void showInventory(List<Product> inventory) {
        if (inventory.isEmpty()) {
            System.out.println("No products registered yet.");
            return;
        }
        System.out.println("\n--- Inventory ---");
        for (Product product : inventory) {
            System.out.println(product);
            System.out.println("------------------------------------------------");
        }
    }

    public void showValuation(double value, String strategyName) {
        System.out.printf("Total inventory valuation (%s): %.2f%n", strategyName, value);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}