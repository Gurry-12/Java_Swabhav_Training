package com.gurpreet.assignment.test;

import java.util.Scanner;
import com.gurpreet.assignment.exceptions.InvalidOrderException;
import com.gurpreet.assignment.models.ExpressOrder;
import com.gurpreet.assignment.models.InternationalOrder;
import com.gurpreet.assignment.models.Order;
import com.gurpreet.assignment.models.StandardOrder;

public class OrderTest {

    private static final int MAX_ORDERS = 10;

    public static void main(String[] args) {

        Order[] orders = new Order[MAX_ORDERS];
        int nextIndex = 0;           // where to insert next order
        Scanner scanner = new Scanner(System.in);

        System.out.println("═══════════════════════════════════════════════");
        System.out.println("     Online Order Fulfillment System v1.0      ");
        System.out.println("═══════════════════════════════════════════════\n");

        while (true) {
            printMainMenu();
            int choice = getValidIntegerInput(scanner, "Enter choice (1–5): ", 1, 5);

            switch (choice) {
                case 1 -> nextIndex = addStandardOrder(orders, nextIndex, scanner);
                case 2 -> nextIndex = addExpressOrder(orders, nextIndex, scanner);
                case 3 -> nextIndex = addInternationalOrder(orders, nextIndex, scanner);
                case 4 -> processAllOrders(orders, nextIndex);
                case 5 -> {
                    System.out.println("\nThank you for using the Order Fulfillment System.");
                    System.out.println("Goodbye.\n");
                    scanner.close();
                    return;
                }
            }
            System.out.println();
        }
    }

    private static void printMainMenu() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│         MAIN MENU           │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ 1. Add Standard Order       │");
        System.out.println("│ 2. Add Express Order        │");
        System.out.println("│ 3. Add International Order  │");
        System.out.println("│ 4. Process All Orders       │");
        System.out.println("│ 5. Exit                     │");
        System.out.println("└─────────────────────────────┘");
    }

    // Returns new nextIndex after possible insertion
    private static int addStandardOrder(Order[] orders, int nextIndex, Scanner scanner) {
        if (nextIndex >= MAX_ORDERS) {
            System.out.println("Error: Maximum number of orders reached (" + MAX_ORDERS + ").");
            return nextIndex;
        }

        System.out.println("\n─── New Standard Order ───");
        Order order = createOrder("standard", scanner);
        if (order != null) {
            orders[nextIndex] = order;
            System.out.println("→ Standard order added successfully. ID: " + order.getOrderId());
            return nextIndex + 1;
        }
        return nextIndex;
    }

    private static int addExpressOrder(Order[] orders, int nextIndex, Scanner scanner) {
        if (nextIndex >= MAX_ORDERS) {
            System.out.println("Error: Maximum number of orders reached (" + MAX_ORDERS + ").");
            return nextIndex;
        }

        System.out.println("\n─── New Express Order ───");
        Order order = createOrder("express", scanner);
        if (order != null) {
            orders[nextIndex] = order;
            System.out.println("→ Express order added successfully. ID: " + order.getOrderId());
            return nextIndex + 1;
        }
        return nextIndex;
    }

    private static int addInternationalOrder(Order[] orders, int nextIndex, Scanner scanner) {
        if (nextIndex >= MAX_ORDERS) {
            System.out.println("Error: Maximum number of orders reached (" + MAX_ORDERS + ").");
            return nextIndex;
        }

        System.out.println("\n─── New International Order ───");
        Order order = createOrder("international", scanner);
        if (order != null) {
            orders[nextIndex] = order;
            System.out.println("→ International order added successfully. ID: " + order.getOrderId());
            return nextIndex + 1;
        }
        return nextIndex;
    }

    private static Order createOrder(String type, Scanner scanner) {
        System.out.print("Customer name : ");
        String name = scanner.nextLine().trim();

        double amount = getValidDoubleInput(scanner, "Order amount (₹) : ", 0.01);

        try {
            return switch (type) {
                case "standard"     -> new StandardOrder(name, amount);
                case "express"      -> new ExpressOrder(name, amount);
                case "international"-> new InternationalOrder(name, amount);
                default             -> null;
            };
        } catch (InvalidOrderException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static void processAllOrders(Order[] orders, int count) {
        if (count == 0) {
            System.out.println("No orders to process.");
            return;
        }

        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("       Processing All Pending Orders           ");
        System.out.println("═══════════════════════════════════════════════\n");

        for (int i = 0; i < count; i++) {
            Order order = orders[i];
            System.out.printf("Order #%d   ID: %s\n", (i + 1), order.getOrderId());
            order.displayOrderSummary();

            try {
                if (order.verifyOrder()) {
                    System.out.println("  Verification: PASSED");
                    order.processOrder();
                }
            } catch (InvalidOrderException e) {
                System.out.println("  Verification FAILED: " + e.getMessage());
            }
            System.out.println("─".repeat(45));
        }

        System.out.println("\nAll orders processed and cleared from active queue.");
        // In real system: move to archive / history instead of just clearing
    }

    // ─── Input Validation Helpers ────────────────────────────────────
    private static int getValidIntegerInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner, String prompt, double min) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= min) {
                    return value;
                }
                System.out.printf("Amount must be at least %.2f%n", min);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}