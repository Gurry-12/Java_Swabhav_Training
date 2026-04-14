package com.collections.inventory.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class Inventory {

    private Set<Product> products;                          // HashSet — no duplicate products
    private Queue<Product> returnQueue;                     // LinkedList — FIFO return requests
    private Map<String, List<Product>> categoryMap;         // HashMap — category-wise products
    private Scanner scanner;

    public Inventory(Scanner scanner) {
        this.products = new HashSet<>();
        this.returnQueue = new LinkedList<>();
        this.categoryMap = new HashMap<>();
        this.scanner = scanner;
    }

    public void addProduct() throws InvalidProductException {
        InventoryUtility.displayProductTypes();
        int choice = Helpers.validateIntRange(scanner, 1, 2);

        Product product;
        switch (choice) {
            case 1:
                product = createElectronicProduct();
                break;
            case 2:
                product = createGroceryProduct();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (products.contains(product)) {
            throw new InvalidProductException(
                "Product already exists with same name and category.");
        }

        products.add(product);

        // Category-wise grouping
        String catKey = product.getCategory();
        if (!categoryMap.containsKey(catKey)) {
            categoryMap.put(catKey, new ArrayList<Product>());
        }
        categoryMap.get(catKey).add(product);

        System.out.println("Product added! ID: " + product.getId());
    }

    private Product createElectronicProduct() throws InvalidProductException {
        System.out.println("Enter Product Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Price (Rs.):");
        double price = Helpers.validateDoublePositive(scanner);

        System.out.println("Enter Warranty (months):");
        int warranty = Helpers.validateIntRange(scanner, 0, 120);

        return new ElectronicProduct(name, price, warranty);
    }

    private Product createGroceryProduct() throws InvalidProductException {
        System.out.println("Enter Product Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Price (Rs.):");
        double price = Helpers.validateDoublePositive(scanner);

        System.out.println("Enter Expiry Date (e.g. 31-12-2025):");
        String expiry = Helpers.validateStringNonEmpty(scanner);

        return new GroceryProduct(name, price, expiry);
    }

    public void viewProductById(String productId) throws InvalidProductException {
        findById(productId).printDetails();
    }

    public void viewAllByName() throws InvalidProductException {
        if (products.isEmpty()) {
            throw new InvalidProductException("No products in inventory.");
        }
        ArrayList<Product> sorted = new ArrayList<>(products);
        Collections.sort(sorted); // natural: by name

        int count = 0;
        for (Product p : sorted) {
            System.out.println("\nProduct " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    public void viewAllByCategoryAndPrice() throws InvalidProductException {
        if (products.isEmpty()) {
            throw new InvalidProductException("No products in inventory.");
        }
        ArrayList<Product> sorted = new ArrayList<>(products);
        Collections.sort(sorted, new CategoryPriceComparator()); // custom: category then price

        int count = 0;
        for (Product p : sorted) {
            System.out.println("\nProduct " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    public void requestReturn(String productId) throws InvalidProductException {
    Product product = findById(productId);
    product.setStatus(ProductStatus.RETURN_REQUESTED);  // mark it
    returnQueue.add(product);
    System.out.println("Return requested for: " + product.getName());
}

public void processNextReturn() {
    if (returnQueue.isEmpty()) {
        System.out.println("No return requests pending.");
        return;
    }
    Product returned = returnQueue.poll();
    returned.setStatus(ProductStatus.RETURNED);         // mark it
    System.out.println("Return processed for:");
    returned.printDetails();
}

    public void viewByCategory(String categoryName) throws InvalidProductException {
        List<Product> catProducts = categoryMap.get(categoryName.toUpperCase());

        if (catProducts == null || catProducts.isEmpty()) {
            throw new InvalidProductException("No products found in category: " + categoryName);
        }

        System.out.println("Products in category: " + categoryName.toUpperCase());
        int count = 0;
        for (Product p : catProducts) {
            System.out.println("\nProduct " + (++count) + ":");
            p.printDetails();
            System.out.println("----------------------------");
        }
    }

    // Iterator-based safe removal of a specific product
    public void removeProduct(String productId) throws InvalidProductException {
        Iterator<Product> iterator = products.iterator();
        Product toRemove = null;

        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getId().equals(productId)) {
                toRemove = p;
                iterator.remove();
                break;
            }
        }

        if (toRemove == null) {
            throw new InvalidProductException("Product not found with ID: " + productId);
        }

        // Clean up category map
        List<Product> catList = categoryMap.get(toRemove.getCategory());
        if (catList != null) {
            catList.remove(toRemove);
        }

        System.out.println("Product removed successfully:");
        System.out.println(" Name : " + toRemove.getName());
        System.out.println(" ID   : " + toRemove.getId());
    }

    private Product findById(String productId) throws InvalidProductException {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        throw new InvalidProductException("Product not found with ID: " + productId);
    }
}