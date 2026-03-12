package com.gurpreet.encapsulation.test;

import java.util.Scanner;
import com.gurpreet.encapsulation.models.Product;
import com.gurpreet.helpers.Helpers;

public class ProductTest {

    

    public static void main(String[] args) {
    	
    	 Scanner scanner = new Scanner(System.in);
         Product product = null;
         
        while (true) {
            showMenu();

            System.out.print("Choice: ");
            int choice = Helpers.validateInt(scanner);
            scanner.nextLine(); // clear newline

            if (choice == 1) createProduct(scanner,product);
            else if (choice == 2) increaseStock(scanner,product);
            else if (choice == 3) reduceStock(scanner,product);
            else if (choice == 4) updatePrice(scanner,product);
            else if (choice == 5) discontinue(scanner,product);
            else if (choice == 6) showProduct(product);
            else if (choice == 7) {
                System.out.println("Goodbye.");
                break;
            }
            else {
                System.out.println("Wrong choice.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("-------------------");
        System.out.println("1. Create product");
        System.out.println("2. Add stock");
        System.out.println("3. Reduce stock");
        System.out.println("4. Change price");
        System.out.println("5. Discontinue");
        System.out.println("6. Show details");
        System.out.println("7. Exit");
        System.out.println("-------------------");
    }

    private static void createProduct(Scanner scanner, Product product) {
        System.out.print("Name: ");
        String name = Helpers.validateString(scanner);

        System.out.print("Price: ");
        double price = Helpers.validateDouble(scanner);

        System.out.print("Stock: ");
        int stock = Helpers.validateInt(scanner);
        scanner.nextLine();

        product = new Product(name, price, stock);
        System.out.println("Product created.");
    }

    private static void increaseStock(Scanner scanner, Product product) {
        if (product == null) {
            System.out.println("No product.");
            return;
        }
        System.out.print("Quantity to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        product.increaseStock(quantity);
        System.out.println("Stock updated.");
    }

    private static void reduceStock(Scanner scanner, Product product) {
        if (product == null) {
            System.out.println("No product.");
            return;
        }
        System.out.print("Quantity to reduce: ");
        int qty = Helpers.validateInt(scanner);
        scanner.nextLine();
        product.reduceStock(qty);
        System.out.println("Stock updated.");
    }

    private static void updatePrice(Scanner scanner, Product product) {
        if (product == null) {
            System.out.println("No product.");
            return;
        }
        System.out.print("New price: ");
        double price = Helpers.validateDouble(scanner);
        scanner.nextLine();
        product.updatePrice(price);
        System.out.println("Price updated.");
    }

    private static void discontinue(Scanner scanner, Product product) {
        if (product == null) {
            System.out.println("No product.");
            return;
        }
        System.out.print("Discontinue? (yes/no): ");
        String ans = Helpers.validateString(scanner);
        if (ans.equalsIgnoreCase("yes")) {
            product.discontinueProduct();
            System.out.println("Product discontinued.");
        }
    }

    private static void showProduct(Product product) {
        if (product == null) {
            System.out.println("No product.");
            return;
        }
        System.out.println("Name       : " + product.getProductName());
        System.out.println("Price      : " + product.getPrice());
        System.out.println("Stock      : " + product.getStockQuantity());
        System.out.println("Discontinued: " + product.isDiscontinued());
    }
}