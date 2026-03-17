package com.gurpreet.comparator.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.comparator.comparators.CategoryPriceComparator;
import com.gurpreet.comparator.models.Product;

public class ProductTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.println("=========================================");
        System.out.println("      Product List ");
        System.out.println("=========================================");

        int n = getPositiveInteger(sc, "How many products do you want to add? ");

        for (int i = 1; i <= n; i++) {
            System.out.println("\nProduct #" + i + ":");
            String category = getNonEmptyString(sc, "Enter category: ");
            String name     = getNonEmptyString(sc, "Enter product name: ");
            double price    = getPositiveDouble(sc, "Enter price (in ₹): ");
            products.add(new Product(category, name, price));
        }

        System.out.println("\nBefore sorting:");
        printProducts(products);

        Collections.sort(products, new CategoryPriceComparator());

        System.out.println("\nAfter sorting (by Category → Price):");
        printProducts(products);

        sc.close();
    }

    private static int getPositiveInteger(Scanner sc, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!sc.hasNextInt()) {
                System.out.print("Enter valid number: ");
                sc.next();
            }
            value = sc.nextInt();
            sc.nextLine();
        } while (value <= 0);
        return value;
    }

    private static double getPositiveDouble(Scanner sc, String prompt) {
        double value;
        do {
            System.out.print(prompt);
            while (!sc.hasNextDouble()) {
                System.out.print("Enter valid price: ");
                sc.next();
            }
            value = sc.nextDouble();
            sc.nextLine();
            if (value <= 0) System.out.println("Price must be positive.");
        } while (value <= 0);
        return value;
    }

    private static String getNonEmptyString(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) System.out.println("Field cannot be empty.");
        } while (input.isEmpty());
        return input;
    }

    private static void printProducts(List<Product> list) {
        System.out.println("Category      Product              Price (₹)");
        System.out.println("-----------------------------------------------");
        for (Product p : list) {
            System.out.printf("%-13s %-20s %,10.0f%n",
                    p.getCategory(), p.getName(), p.getPrice());
        }
    }
}