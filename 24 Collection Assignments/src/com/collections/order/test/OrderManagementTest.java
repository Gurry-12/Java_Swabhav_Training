package com.collections.order.test;

import java.util.Scanner;

import com.collections.order.models.Helpers;
import com.collections.order.models.InvalidOrderException;
import com.collections.order.models.OrderManager;
import com.collections.order.models.OrderUtility;

public class OrderManagementTest {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {

			OrderManager manager = new OrderManager(scanner);

			System.out.println("-----------------------------------");
			System.out.println("  Order Processing & Dispatch System");
			System.out.println("-----------------------------------\n");

			boolean isRun = true;
			while (isRun) {
				try {
					OrderUtility.displayMenu();
					int choice = Helpers.validateIntRange(scanner, 1, 9);

					String orderId;
					switch (choice) {

					case 1:
						manager.placeOrder();
						break;

					case 2:
						orderId = Helpers.validateOrderId(scanner);
						manager.viewOrderById(orderId);
						break;

					case 3:
						manager.viewAllOrders();
						break;

					case 4:
						orderId = Helpers.validateOrderId(scanner);
						manager.cancelOrder(orderId);
						break;

					case 5:
						manager.dispatchNextOrder();
						break;

					case 6:
						System.out.println("Enter Customer Name:");
						String customerName = Helpers.validateStringNonEmpty(scanner);
						manager.viewOrdersByCustomer(customerName);
						break;

					case 7:
						System.out.println("Enter search term (customer name or item):");
						String searchTerm = Helpers.validateStringNonEmpty(scanner);
						manager.searchOrders(searchTerm);
						break;

					case 8:
						manager.removeCancelledOrders();
						break;

					case 9:
						isRun = false;
						System.out.println("Thank you for using Order Management System!");
						break;

					default:
						System.out.println("Enter valid input.");
					}

				} catch (InvalidOrderException e) {
					System.out.println(e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}