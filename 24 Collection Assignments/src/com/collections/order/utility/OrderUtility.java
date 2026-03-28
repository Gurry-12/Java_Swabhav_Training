package com.collections.order.utility;

import java.util.Scanner;

import com.collections.order.enums.PriorityLevel;

public class OrderUtility {

    public static void displayOrderTypes() {
        System.out.println("Select Order Type:");
        System.out.println("1. Regular Order");
        System.out.println("2. Priority Order");
    }

    public static PriorityLevel printAndGetPriorityLevel(Scanner scanner) {
        PriorityLevel[] levels = PriorityLevel.values();
        System.out.println("Select Priority Level:");
        for (int i = 0; i < levels.length; i++) {
            System.out.println((i + 1) + " : " + levels[i]);
        }
        int choice = Helpers.validateIntRange(scanner, 1, levels.length);
        return levels[choice - 1];
    }

    public static void displayMenu() {
        System.out.println("\n======= Order Management System =======");
        System.out.println("1. Place Order");
        System.out.println("2. View Order by ID");
        System.out.println("3. View All Orders");
        System.out.println("4. Cancel Order");
        System.out.println("5. Dispatch Next Order (Queue)");
        System.out.println("6. View Orders by Customer");
        System.out.println("7. Search Orders");
        System.out.println("8. Remove Cancelled Orders");
        System.out.println("9. Exit");
        System.out.println("========================================");
    }
}