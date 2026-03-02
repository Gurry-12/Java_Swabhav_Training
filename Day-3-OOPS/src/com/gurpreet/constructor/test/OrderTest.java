package com.gurpreet.constructor.test;

import java.util.Scanner;
import com.gurpreet.constructor.models.Product;
import com.gurpreet.constructor.models.Order;
import com.gurpreet.constructor.models.DiscountedOrder;
import com.gurpreet.helpers.Helpers;

/**
 * Test for Question 3: E-Commerce Product & Order System
 */
public class OrderTest {

	private static final int MAX_PRODUCTS = 10;
	private static final int MAX_ORDERS = 20;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Product[] products = new Product[MAX_PRODUCTS];
		Order[] orders = new Order[MAX_ORDERS];
		int productCount = 0;
		int orderCount = 0;

		System.out.println("========================================");
		System.out.println("  E-COMMERCE SYSTEM - CONSTRUCTOR DEMO");
		System.out.println("========================================\n");

		boolean running = true;
		while (running) {
			displayMenu();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				if (productCount < MAX_PRODUCTS) {
					Product product = createProduct(scanner);
					if (product != null) {
						products[productCount++] = product;
					}
				} else {
					System.out.println("Maximum product limit reached.");
				}
				break;

			case 2:
				displayAllProducts(products, productCount);
				break;

			case 3:
				if (orderCount < MAX_ORDERS) {
					Order order = placeRegularOrder(scanner, products, productCount);
					if (order != null) {
						orders[orderCount++] = order;
					}
				} else {
					System.out.println("Maximum order limit reached.");
				}
				break;

			case 4:
				if (orderCount < MAX_ORDERS) {
					Order order = placeDiscountedOrder(scanner, products, productCount);
					if (order != null) {
						orders[orderCount++] = order;
					}
				} else {
					System.out.println("Maximum order limit reached.");
				}
				break;

			case 5:
				displayAllOrders(orders, orderCount);
				break;

			case 6:
				displayOrderSummary(orders, orderCount);
				break;

			case 7:
				demonstrateValidation(products, productCount);
				break;

			case 8:
				running = false;
				System.out.println("Thank you for using E-Commerce System.");
				break;

			default:
				System.out.println("Invalid choice. Please enter 1-8.");
			}

			System.out.println();
		}

		scanner.close();
	}

	private static void displayMenu() {
		System.out.println("-----------------------------");
		System.out.println("     E-Commerce Menu");
		System.out.println("1. Add Product");
		System.out.println("2. Display All Products");
		System.out.println("3. Place Regular Order");
		System.out.println("4. Place Discounted Order");
		System.out.println("5. Display All Orders");
		System.out.println("6. Display Order Summary");
		System.out.println("7. Demonstrate Constructor Validation");
		System.out.println("8. Exit");
		System.out.println("-----------------------------");
	}

	private static Product createProduct(Scanner scanner) {
		System.out.println("\n--- Add Product ---");
		
		System.out.print("Enter product name: ");
		String name = Helpers.validateString(scanner);

		System.out.print("Enter price: ");
		double price = Helpers.validateDouble(scanner);

		System.out.print("Enter stock quantity: ");
		int stock = Helpers.validateInt(scanner);
		scanner.nextLine();

		try {
			Product product = new Product(name, price, stock);
			System.out.println("✓ Product added successfully!");
			return product;
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
			return null;
		}
	}

	private static void displayAllProducts(Product[] products, int count) {
		if (count == 0) {
			System.out.println("No products available.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("         ALL PRODUCTS");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			System.out.println("Product #" + (i + 1));
			System.out.println("----------------------------------------");
			products[i].displayProductInfo();
			System.out.println("----------------------------------------\n");
		}
	}

	private static Order placeRegularOrder(Scanner scanner, Product[] products, int productCount) {
		if (productCount == 0) {
			System.out.println("No products available to order.");
			return null;
		}

		System.out.println("\n--- Place Regular Order ---");
		displayAllProducts(products, productCount);

		System.out.print("Enter product number (1-" + productCount + "): ");
		int productNum = Helpers.validateInt(scanner);

		if (productNum < 1 || productNum > productCount) {
			System.out.println("Invalid product number.");
			return null;
		}

		System.out.print("Enter quantity: ");
		int quantity = Helpers.validateInt(scanner);
		scanner.nextLine();

		try {
			Order order = new Order(products[productNum - 1], quantity);
			System.out.println("✓ Order placed successfully!");
			return order;
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
			return null;
		}
	}

	private static Order placeDiscountedOrder(Scanner scanner, Product[] products, int productCount) {
		if (productCount == 0) {
			System.out.println("No products available to order.");
			return null;
		}

		System.out.println("\n--- Place Discounted Order ---");
		displayAllProducts(products, productCount);

		System.out.print("Enter product number (1-" + productCount + "): ");
		int productNum = Helpers.validateInt(scanner);

		if (productNum < 1 || productNum > productCount) {
			System.out.println("Invalid product number.");
			return null;
		}

		System.out.print("Enter quantity: ");
		int quantity = Helpers.validateInt(scanner);

		System.out.print("Enter discount percentage: ");
		double discount = Helpers.validateDouble(scanner);
		scanner.nextLine();

		try {
			DiscountedOrder order = new DiscountedOrder(products[productNum - 1], quantity, discount);
			System.out.println("✓ Discounted order placed successfully!");
			return order;
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Error: " + e.getMessage());
			return null;
		}
	}

	private static void displayAllOrders(Order[] orders, int count) {
		if (count == 0) {
			System.out.println("No orders placed yet.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("         ALL ORDERS");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			System.out.println("Order #" + (i + 1));
			System.out.println("----------------------------------------");
			orders[i].displayOrderInfo();
			System.out.println("----------------------------------------\n");
		}
	}

	private static void displayOrderSummary(Order[] orders, int count) {
		if (count == 0) {
			System.out.println("No orders to summarize.");
			return;
		}

		double totalRevenue = 0;

		System.out.println("\n========================================");
		System.out.println("         ORDER SUMMARY");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			totalRevenue += orders[i].getTotalAmount();
		}

		System.out.println("Total Orders: " + count);
		System.out.println("Total Revenue: ₹" + totalRevenue);
		System.out.println("Average Order Value: ₹" + (totalRevenue / count));
		System.out.println("========================================");
	}

	private static void demonstrateValidation(Product[] products, int productCount) {
		System.out.println("\n========================================");
		System.out.println("  CONSTRUCTOR VALIDATION DEMONSTRATION");
		System.out.println("========================================\n");

		// Test 1: Negative price
		System.out.println("Test 1: Trying to create product with negative price...");
		try {
			Product p1 = new Product("Invalid Product", -100, 10);
			System.out.println("✗ Should have failed!");
		} catch (IllegalArgumentException e) {
			System.out.println("✓ Correctly rejected: " + e.getMessage());
		}

		// Test 2: Null product in order
		System.out.println("\nTest 2: Trying to create order with null product...");
		try {
			Order o1 = new Order(null, 5);
			System.out.println("✗ Should have failed!");
		} catch (IllegalArgumentException e) {
			System.out.println("✓ Correctly rejected: " + e.getMessage());
		}

		// Test 3: Zero quantity
		System.out.println("\nTest 3: Trying to create order with zero quantity...");
		if (productCount > 0) {
			try {
				Order o2 = new Order(products[0], 0);
				System.out.println("✗ Should have failed!");
			} catch (IllegalArgumentException e) {
				System.out.println("✓ Correctly rejected: " + e.getMessage());
			}
		} else {
			System.out.println("(Skipped - no products available)");
		}

		// Test 4: Valid product and order
		System.out.println("\nTest 4: Creating valid product and order...");
		try {
			Product p4 = new Product("Test Product", 500, 20);
			System.out.println("✓ Product created!");
			
			Order o4 = new Order(p4, 3);
			System.out.println("✓ Order created!");
			o4.displayOrderInfo();
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Unexpected error: " + e.getMessage());
		}

		// Test 5: Demonstrate total calculation at creation
		System.out.println("\nTest 5: Demonstrating total calculation at creation...");
		try {
			Product p5 = new Product("Demo Product", 1000, 10);
			System.out.println("Product price: ₹" + p5.getPrice());
			
			Order o5 = new Order(p5, 5);
			System.out.println("Order quantity: " + o5.getQuantity());
			System.out.println("Total calculated at creation: ₹" + o5.getTotalAmount());
			System.out.println("✓ Total is immutable and calculated correctly!");
		} catch (IllegalArgumentException e) {
			System.out.println("✗ Unexpected error: " + e.getMessage());
		}

		System.out.println("\n========================================");
	}

}
