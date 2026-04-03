package com.ims.model.inventorymodel;

import java.util.List;
import java.util.Scanner;

import com.ims.model.Product;
import com.ims.model.ProductType;
import com.ims.model.exception.DuplicateProductException;
import com.ims.model.exception.InsufficientStockException;
import com.ims.model.exception.ProductNotFoundException;
import com.ims.model.valuationmodel.FIFOValuation;
import com.ims.model.valuationmodel.LIFOValuation;
import com.ims.model.valuationmodel.ValuationStrategy;

public class InventoryController {

	private final InventoryService inventoryService;
	private final MenuRenderer renderer;

	public InventoryController(InventoryService inventoryService, MenuRenderer renderer) {
		this.inventoryService = inventoryService;
		this.renderer = renderer;
	}

	public void start(Scanner scanner) {

		renderer.showHeader();

		boolean isRun = true;
		while (isRun) {

			renderer.showMenu();
			int choice = Helpers.validateInt(scanner);

			switch (choice) {
			case 1:
				addProduct(scanner);
				break;
			case 2:
				addStock(scanner);
				break;
				
			case 3: 
				removeStock(scanner);
				break;
			case 4:
				calculateValuation();
				break;
				
			case 5:
				showInventory();
				break;
				
			case 6:
				switchStrategy(scanner);
				break;
				
			case 7:
			{
				isRun = false;
				renderer.showMessage("Thank you for visiting.");
				break;
			}
			default:
				renderer.showMessage("Invalid choice. Please try again.");
			}
		}
	}

	private void addProduct(Scanner scanner) {
		renderer.showMessage("\nEnter product details:");
		renderer.showMessage("Name:");
		String name = Helpers.validateName(scanner);

		ProductType type = getProductType(scanner);

		renderer.showMessage("Threshold:");
		int threshold = Helpers.validateThreshold(scanner);

		renderer.showMessage("Quantity:");
		int stock = Helpers.validateStock(scanner, threshold);

		renderer.showMessage("Price per unit:");
		double price = Helpers.validatePrice(scanner);

		try {
			Product added = inventoryService.addProduct(name, type, threshold, stock, price);
			renderer.showMessage("Product added: " + added.getName() + " (ID: " + added.getId() + ")");
		} catch (DuplicateProductException e) {
			renderer.showMessage("Error: " + e.getMessage());
		}
	}

	private void addStock(Scanner scanner) {
		renderer.showMessage("Enter Product ID:");
		int id = Helpers.validateIntPositive(scanner);
		renderer.showMessage("Enter Quantity:");
		int quantity = Helpers.validateIntPositive(scanner);

		try {
			inventoryService.addInventory(id, quantity);
			renderer.showMessage("Stock updated successfully.");
		} catch (ProductNotFoundException e) {
			renderer.showMessage("Error: " + e.getMessage());
		}
	}

	private void removeStock(Scanner scanner) {
		renderer.showMessage("Enter Product ID:");
		int id = Helpers.validateIntPositive(scanner);
		renderer.showMessage("Enter Quantity:");
		int quantity = Helpers.validateIntPositive(scanner);

		try {
			inventoryService.removeInventory(id, quantity);
			renderer.showMessage("Stock removed successfully.");
		} catch (ProductNotFoundException | InsufficientStockException e) {
			renderer.showMessage("Error: " + e.getMessage());
		}
	}

	private void calculateValuation() {
		double value = inventoryService.calculateValuation();
		renderer.showValuation(value, inventoryService.getValuationStrategyName());
	}

	private void showInventory() {
		List<Product> inventory = inventoryService.getInventory();
		renderer.showInventory(inventory);
	}

	private void switchStrategy(Scanner scanner) {
		renderer.showStrategyMenu();
		int choice = Helpers.validateIntRange(scanner, 1, 2);

		ValuationStrategy strategy = switch (choice) {
		case 1 -> new FIFOValuation();
		case 2 -> new LIFOValuation();
		default -> throw new IllegalStateException("Invalid strategy choice"); 
		};

		inventoryService.setValuationStrategy(strategy);
		renderer.showMessage("Strategy switched to: " + inventoryService.getValuationStrategyName());
	}

	private ProductType getProductType(Scanner scanner) {
		ProductType[] types = ProductType.values();
		renderer.showMessage("Select Product Type:");
		for (int i = 0; i < types.length; i++) {
			renderer.showMessage((i + 1) + ". " + types[i]);
		}
		int choice = Helpers.validateIntRange(scanner, 1, types.length);
		return types[choice - 1];
	}
}