package com.collections.inventory.test;

import java.util.Scanner;

import com.collections.inventory.exceptions.InvalidProductException;
import com.collections.inventory.models.Inventory;
import com.collections.inventory.utility.Helpers;
import com.collections.inventory.utility.InventoryUtility;

public class InventoryManagementTest {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Inventory inventory = new Inventory(scanner);

            System.out.println("-----------------------------------");
            System.out.println("  Product Inventory & Return System");
            System.out.println("-----------------------------------\n");

            boolean isRun = true;
            while (isRun) {
                try {
                    InventoryUtility.displayMenu();
                    int choice = Helpers.validateIntRange(scanner, 1, 9);

                    switch (choice) {

                        case 1:
                            inventory.addProduct();
                            break;

                        case 2:
                            String productId = Helpers.validateProductId(scanner);
                            inventory.viewProductById(productId);
                            break;

                        case 3:
                            inventory.viewAllByName();
                            break;

                        case 4:
                            inventory.viewAllByCategoryAndPrice();
                            break;

                        case 5:
                            productId = Helpers.validateProductId(scanner);
                            inventory.requestReturn(productId);
                            break;

                        case 6:
                            inventory.processNextReturn();
                            break;

                        case 7:
                            System.out.println("Enter Category (ELECTRONICS / GROCERY):");
                            String category = Helpers.validateStringNonEmpty(scanner);
                            inventory.viewByCategory(category);
                            break;

                        case 8:
                            productId = Helpers.validateProductId(scanner);
                            inventory.removeProduct(productId);
                            break;

                        case 9:
                            isRun = false;
                            System.out.println("Goodbye!");
                            break;

                        default:
                            System.out.println("Enter valid input.");
                    }

                } catch (InvalidProductException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}