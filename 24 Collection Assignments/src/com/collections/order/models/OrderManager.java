package com.collections.order.models;

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

import com.collections.library.utility.Helpers;
import com.collections.order.abstracts.Order;
import com.collections.order.comparator.CustomerAmountComparator;
import com.collections.order.enums.OrderStatus;
import com.collections.order.enums.PriorityLevel;
import com.collections.order.exceptions.InvalidOrderException;
import com.collections.order.utility.OrderUtility;

public class OrderManager {

    private Set<Order> orders;                         // HashSet — no duplicate orders
    private Queue<Order> dispatchQueue;                // LinkedList — FIFO dispatch processing
    private Set<String> processedOrderIds;             // HashSet — avoid duplicate dispatch
    private Map<String, List<Order>> customerOrderMap; // HashMap — customer to orders mapping
    private Scanner scanner;

    public OrderManager(Scanner scanner) {
        this.orders = new HashSet<>();
        this.dispatchQueue = new LinkedList<>();
        this.processedOrderIds = new HashSet<>();
        this.customerOrderMap = new HashMap<>();
        this.scanner = scanner;
    }

    public void placeOrder() throws InvalidOrderException {
        OrderUtility.displayOrderTypes();
        int choice = Helpers.validateIntRange(scanner, 1, 2);

        Order order;
        switch (choice) {
            case 1:
                order = createRegularOrder();
                break;
            case 2:
                order = createPriorityOrder();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (orders.contains(order)) {
            throw new InvalidOrderException("Duplicate order — same customer has already ordered this item.");
        }

        orders.add(order);
        dispatchQueue.add(order);

        // Maintain customer-to-orders mapping
        String key = order.getCustomerName().toLowerCase();
        if (!customerOrderMap.containsKey(key)) {
            customerOrderMap.put(key, new ArrayList<Order>());
        }
        customerOrderMap.get(key).add(order);

        System.out.println("Order placed successfully! Order ID: " + order.getId());
    }

    private Order createRegularOrder() throws InvalidOrderException {
        System.out.println("Enter Customer Name:");
        String customerName = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Item Description:");
        String itemDescription = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Total Amount (Rs.):");
        double amount = Helpers.validateDoublePositive(scanner);

        System.out.println("Enter Estimated Delivery Days:");
        int days = Helpers.validateIntRange(scanner, 1, 365);

        return new RegularOrder(customerName, itemDescription, amount, days);
    }

    private Order createPriorityOrder() throws InvalidOrderException {
        System.out.println("Enter Customer Name:");
        String customerName = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Item Description:");
        String itemDescription = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Total Amount (Rs.):");
        double amount = Helpers.validateDoublePositive(scanner);

        PriorityLevel level = OrderUtility.printAndGetPriorityLevel(scanner);

        return new PriorityOrder(customerName, itemDescription, amount, level);
    }

    public void viewOrderById(String orderId) throws InvalidOrderException {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                order.printDetails();
                return;
            }
        }
        throw new InvalidOrderException("Order not found with ID: " + orderId);
    }

    public void viewAllOrders() throws InvalidOrderException {
        if (orders.isEmpty()) {
            throw new InvalidOrderException("No orders found.");
        }

        ArrayList<Order> sorted = new ArrayList<Order>(orders);
        Collections.sort(sorted, new CustomerAmountComparator());

        int count = 0;
        for (Order order : sorted) {
            System.out.println("\nOrder " + (++count) + ":");
            order.printDetails();
            System.out.println("============================");
        }
    }

    public void cancelOrder(String orderId) throws InvalidOrderException {
        Order orderToCancel = null;

        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                orderToCancel = order;
                break;
            }
        }

        if (orderToCancel == null) {
            throw new InvalidOrderException("Order not found with ID: " + orderId);
        }

        if (orderToCancel.getStatus() == OrderStatus.DISPATCHED) {
            throw new InvalidOrderException("Cannot cancel — order is already dispatched.");
        }

        if (orderToCancel.getStatus() == OrderStatus.CANCELLED) {
            throw new InvalidOrderException("Order is already cancelled.");
        }

        orderToCancel.setStatus(OrderStatus.CANCELLED);
        System.out.println("Order " + orderId + " cancelled successfully.");
    }

    public void dispatchNextOrder() throws InvalidOrderException {
        // Skip cancelled orders sitting at the front of the queue
        while (!dispatchQueue.isEmpty()
                && dispatchQueue.peek().getStatus() == OrderStatus.CANCELLED) {
            dispatchQueue.poll();
        }

        if (dispatchQueue.isEmpty()) {
            System.out.println("No pending orders in dispatch queue.");
            return;
        }

        Order orderToDispatch = dispatchQueue.poll();

        if (processedOrderIds.contains(orderToDispatch.getId())) {
            throw new InvalidOrderException("Order already processed: " + orderToDispatch.getId());
        }

        orderToDispatch.setStatus(OrderStatus.DISPATCHED);
        processedOrderIds.add(orderToDispatch.getId());

        System.out.println("Order dispatched successfully:");
        orderToDispatch.printDetails();
    }

    public void viewOrdersByCustomer(String customerName) throws InvalidOrderException {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidOrderException("Customer name cannot be empty.");
        }

        String key = customerName.trim().toLowerCase();
        List<Order> customerOrders = customerOrderMap.get(key);

        if (customerOrders == null || customerOrders.isEmpty()) {
            throw new InvalidOrderException("No orders found for customer: " + customerName);
        }

        System.out.println("Orders for customer \"" + customerName + "\":");
        int count = 0;
        for (Order order : customerOrders) {
            System.out.println("\nOrder " + (++count) + ":");
            order.printDetails();
            System.out.println("----------------------------");
        }
    }

    public void searchOrders(String searchTerm) throws InvalidOrderException {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new InvalidOrderException("Search term cannot be empty.");
        }

        String term = searchTerm.trim().toLowerCase();
        int count = 0;

        System.out.println("Search results for \"" + searchTerm + "\":");

        for (Order order : orders) {
            if (order.getCustomerName().toLowerCase().contains(term)
                    || order.getItemDescription().toLowerCase().contains(term)) {
                System.out.println("\nMatch " + (++count) + ":");
                order.printDetails();
                System.out.println("----------------------------");
            }
        }

        if (count == 0) {
            throw new InvalidOrderException("No orders found matching: " + searchTerm);
        }
    }

    // Iterator-based safe removal of all cancelled orders
    public void removeCancelledOrders() {
        Iterator<Order> iterator = orders.iterator();
        int removedCount = 0;

        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getStatus() == OrderStatus.CANCELLED) {

                // Also clean up customer map entry
                String key = order.getCustomerName().toLowerCase();
                List<Order> customerOrders = customerOrderMap.get(key);
                if (customerOrders != null) {
                    customerOrders.remove(order);
                }

                iterator.remove();
                removedCount++;
            }
        }

        if (removedCount == 0) {
            System.out.println("No cancelled orders to remove.");
        } else {
            System.out.println(removedCount + " cancelled order(s) removed successfully.");
        }
    }
}