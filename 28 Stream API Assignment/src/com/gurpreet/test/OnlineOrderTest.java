package com.gurpreet.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.model.Order;
import com.gurpreet.service.OnlineOrderProcessingSystem;

public class OnlineOrderTest {

	public static void main(String[] args) {
		List<Order> orders = new ArrayList<>();
		OnlineOrderProcessingSystem processor = new OnlineOrderProcessingSystem();
		Scanner scanner = new Scanner(System.in);

		int choice;

		System.out.println("==================================================");
		System.out.println("     ONLINE ORDER PROCESSING SYSTEM");
		System.out.println("==================================================\n");
		System.out.println("System initialized with no orders.\n");
		System.out.println("Please use Option 1 to add orders.\n");

		try {
			do {
				displayMenu();
				System.out.print("Enter your choice (0-9): ");

				while (!scanner.hasNextInt()) {
					System.out.println("Invalid input! Please enter a valid number.");
					scanner.next();
				}

				choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					processor.addNewOrder(scanner, orders);
					break;
				case 2:
					processor.getAllCompletedOrders(orders);
					break;
				case 3:
					processor.calculateTotalRevenue(orders);
					break;
				case 4:
					processor.groupOrdersByCategory(orders);
					break;
				case 5:
					processor.findOrderWithMaximumAmount(orders);
					break;
				case 6:
					processor.countCancelledOrders(orders);
					break;
				case 7:
					processor.getOrderIdToAmountMap(orders);
					break;
				case 8:
					processor.getCustomerNamesSortedByAmountDescending(orders);
					break;
				case 9:
					processor.displayAllOrders(orders);
					break;
				case 0:
					System.out.println("\nThank you for using Online Order Processing System. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice! Please enter a number between 0 and 9.");
				}

				if (choice != 0) {
					System.out.println("\n" + "=".repeat(60) + "\n");
				}

			} while (choice != 0);

		} catch (Exception e) {
			System.out.println("\nAn unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	private static void displayMenu() {
		System.out.println("------------------- MAIN MENU -------------------");
		System.out.println("1.  Add New Order");
		System.out.println("2.  Get All Completed Orders");
		System.out.println("3.  Calculate Total Revenue (Completed)");
		System.out.println("4.  Group Orders by Category");
		System.out.println("5.  Find Order with Maximum Amount");
		System.out.println("6.  Count Cancelled Orders");
		System.out.println("7.  Get Order ID -> Amount Map");
		System.out.println("8.  Customer Names Sorted by Amount (Descending)");
		System.out.println("9.  Display All Orders");
		System.out.println("0.  Exit");
		System.out.println("-------------------------------------------------");
	}
}