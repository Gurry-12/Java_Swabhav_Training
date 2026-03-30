package com.gurpreet.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.gurpreet.enums.Category;
import com.gurpreet.enums.OrderStatus;
import com.gurpreet.exception.InvalidAmountException;
import com.gurpreet.exception.InvalidIdException;
import com.gurpreet.model.Order;
import com.gurpreet.utility.Helpers;

public class OnlineOrderProcessingSystem {

	public void getAllCompletedOrders(List<Order> orders) {
		System.out.println("\n--- All Completed Orders ---");
		List<Order> completed = orders.stream().filter(o -> "COMPLETED".equals(o.getStatus()))
				.collect(Collectors.toList());

		if (completed.isEmpty()) {
			System.out.println("No completed orders found.");
			return;
		}
		completed.forEach(System.out::println);
	}

	public void calculateTotalRevenue(List<Order> orders) {
		System.out.println("\n--- Total Revenue from Completed Orders ---");
		double totalRevenue = orders.stream().filter(o -> "COMPLETED".equals(o.getStatus()))
				.mapToDouble(Order::getAmount).sum();

		System.out.printf("Total Revenue : ₹%.2f%n", totalRevenue);
	}

	public void groupOrdersByCategory(List<Order> orders) {
		System.out.println("\n--- Orders Grouped by Category ---");
		Map<String, List<Order>> grouped = orders.stream().collect(Collectors.groupingBy(Order::getCategory));

		grouped.forEach((category, list) -> {
			System.out.println("\nCategory: " + category);
			list.forEach(System.out::println);
		});
	}

	public void findOrderWithMaximumAmount(List<Order> orders) {
		System.out.println("\n--- Order with Maximum Amount ---");
		Optional<Order> maxOrder = orders.stream().max(Comparator.comparingDouble(Order::getAmount));

		if (maxOrder.isPresent()) {
			System.out.println(maxOrder.get());
		} else {
			System.out.println("No orders found.");
		}
	}

	public void countCancelledOrders(List<Order> orders) {
		System.out.println("\n--- Cancelled Orders Count ---");
		long cancelledCount = orders.stream().filter(o -> "CANCELLED".equals(o.getStatus())).count();

		System.out.println("Total Cancelled Orders: " + cancelledCount);
	}

	public void getOrderIdToAmountMap(List<Order> orders) {
		System.out.println("\n--- Order ID to Amount Mapping ---");
		Map<String, Double> orderMap = orders.stream().collect(Collectors.toMap(Order::getOrderId, Order::getAmount));

		orderMap.forEach((id, amount) -> System.out.printf("%-12s : ₹%.2f%n", id, amount));
	}

	public void getCustomerNamesSortedByAmountDescending(List<Order> orders) {
		System.out.println("\n--- Customer Names Sorted by Order Amount (Descending) ---");
		List<String> sortedCustomers = orders.stream().sorted(Comparator.comparingDouble(Order::getAmount).reversed())
				.map(Order::getCustomerName).collect(Collectors.toList());

		for (int i = 0; i < sortedCustomers.size(); i++) {
			System.out.println((i + 1) + ". " + sortedCustomers.get(i));
		}
	}

	public void displayAllOrders(List<Order> orders) {
		System.out.println("\n--- All Orders ---");
		if (orders.isEmpty()) {
			System.out.println("No orders in the system.");
		} else {
			orders.forEach(System.out::println);
		}
	}

	// Add New Order
	public void addNewOrder(Scanner scanner, List<Order> orders) {
		System.out.println("\n=== Add New Order ===");

		try {
			String orderId = getValidOrderId(scanner, orders);
			String customerName = getValidCustomerName(scanner);
			Category category = getValidCategory(scanner);
			double amount = getValidAmount(scanner);
			OrderStatus status = getValidStatus(scanner);

			Order newOrder = new Order(orderId, customerName, category, amount, status);
			orders.add(newOrder);

			System.out.println("\nOrder placed successfully!");
			System.out.println(newOrder);

		} catch (InvalidIdException | InvalidAmountException e) {
			System.out.println("Error: " + ((Throwable) e).getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
	}

	// Helper Methods
	private String getValidOrderId(Scanner scanner, List<Order> orders) {
		while (true) {
			System.out.print("Enter Order ID (e.g. ORD123): ");
			String id = scanner.nextLine().trim().toUpperCase();
			if (id.isEmpty()) {
				System.out.println("Order ID cannot be empty.");
				continue;
			}
			if (orders.stream().anyMatch(o -> o.getOrderId().equals(id))) {
				System.out.println("Order ID already exists. Please enter unique ID.");
				continue;
			}
			return id;
		}
	}

	private String getValidCustomerName(Scanner scanner) {
		System.out.print("Enter Customer Name: ");
		return Helpers.validateStringLettersOnly(scanner);
	}

	private Category getValidCategory(Scanner scanner) {
		Category[] categories = Category.values();

		System.out.println("Select Category:");
		for (int i = 0; i < categories.length; i++) {
			System.out.println((i + 1) + " : " + categories[i]);
		}
		int choice = Helpers.validateIntRange(scanner, 1, categories.length);
		return categories[choice - 1];
	}

	private double getValidAmount(Scanner scanner) {
		System.out.print("Enter Order Amount (₹): ");
		return Helpers.validateDoublePositive(scanner);
	}

	private OrderStatus getValidStatus(Scanner scanner) {
		OrderStatus[] status = OrderStatus.values();

		System.out.println("Select Status:");
		for (int i = 0; i < status.length; i++) {
			System.out.println((i + 1) + " : " + status[i]);
		}
		int choice = Helpers.validateIntRange(scanner, 1, status.length);
		return status[choice - 1];
	}
}