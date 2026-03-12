package com.gurpreet.oopsassignment.test;

import java.util.Scanner;

import com.gurpreet.oopsassignment.enums.Material;
import com.gurpreet.oopsassignment.enums.Sizes;
import com.gurpreet.oopsassignment.helpers.Helpers;
import com.gurpreet.oopsassignment.models.Clothing;
import com.gurpreet.oopsassignment.models.Electronics;
import com.gurpreet.oopsassignment.models.Product;

public class ProductTest {

    private static final int MAX_PRODUCTS = 10;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Product[] inventory = new Product[MAX_PRODUCTS];
        int productCount = 0;

        System.out.println("=====================================");
        System.out.println("      Product Inventory System");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {

            displayMainMenu();

            System.out.print("Enter your choice (1-4): ");
            int choice = Helpers.validateIntRange(scanner, 1, 4);

            switch (choice) {
                case 1:
                    if (productCount >= MAX_PRODUCTS) {
                        System.out.println("\nInventory full (" + MAX_PRODUCTS + " products maximum).");
                        break;
                    }
                    productCount = addProduct(scanner, inventory, productCount);
                    break;

                case 2:
                    displayInventory(inventory, productCount);
                    break;

                case 3:
                    System.out.println("\nTotal products in inventory: " + productCount);
                    break;

                case 4:
                    running = false;
                    System.out.println("\nThank you for using the system. Goodbye.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add new product");
        System.out.println("2. Display full inventory");
        System.out.println("3. Show total number of products");
        System.out.println("4. Exit");
    }

    private static int addProduct(Scanner scanner, Product[] inventory, int currentCount) {
        System.out.println("\n--- Add New Product ---");


        System.out.print("Enter product name: ");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.print("Enter base price: ");
        double price = Helpers.validateDoublePositive(scanner);

        System.out.println("\nSelect category:");
        System.out.println("  1) Electronics");
        System.out.println("  2) Clothing");
        int category = Helpers.validateIntRange(scanner, 1, 2);

        Product newProduct = null;
        
        switch(category) {
        case 1:  {
            System.out.print("Enter warranty period (years): ");
            int warranty = Helpers.validateIntNonNegative(scanner);

            System.out.print("Enter brand name: ");
            String brand = Helpers.validateStringNonEmpty(scanner);

            newProduct = new Electronics(name, price, warranty, brand);
            break;
        } 
        case 2: {
            System.out.println("\nSelect size:");
            Sizes[] sizes = Sizes.values();
            for (int i = 0; i < sizes.length; i++) {
                System.out.printf("  %d) %s%n", i + 1, sizes[i]);
            }
            int sizeChoice = Helpers.validateIntRange(scanner, 1, sizes.length) - 1;

            System.out.println("\nSelect material:");
            Material[] materials = Material.values();
            for (int i = 0; i < materials.length; i++) {
                System.out.printf("  %d) %s%n", i + 1, materials[i]);
            }
            int matChoice = Helpers.validateIntRange(scanner, 1, materials.length) - 1;

            newProduct = new Clothing(name, price, sizes[sizeChoice], materials[matChoice]);
        }
        
        default:
        	System.out.println("Enter valid Input");
        }
        
        inventory[currentCount] = newProduct;
        System.out.println("\nProduct added successfully.");
        newProduct.displayInventory();
        return currentCount + 1;
    }

    private static void displayInventory(Product[] inventory, int count) {
        if (count == 0) {
            System.out.println("\nInventory is empty.");
            return;
        }

        System.out.println("\n=== Current Inventory ===");
        for (int i = 0; i < count; i++) {
            inventory[i].displayInventory();
            System.out.println("----------------------------------------");
        }
    }
}