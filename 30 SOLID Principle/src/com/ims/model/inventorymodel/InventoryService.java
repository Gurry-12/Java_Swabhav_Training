package com.ims.model.inventorymodel;

import java.util.LinkedList;
import java.util.List;

import com.ims.model.Product;
import com.ims.model.valuationmodel.ValuationStrategy;

public class InventoryService {

	private ReorderService reorderService;
	private ValuationStrategy valuationStrategy;

	List<Product> inventory = new LinkedList<>();

	public InventoryService(ReorderService reorderService, ValuationStrategy valuationStrategy) {
		this.reorderService = reorderService;
		this.valuationStrategy = valuationStrategy;
	}

	public double calculateValuation() {
		return valuationStrategy.calculate(inventory);
	}

	public void removeInventory(int productId, int quantity) {
		if (inventory.isEmpty()) {
			System.out.println("No product registerd yet");
			return;
		}

		Product product = findProductById(productId);
		if (product == null) {
			System.out.println("Product with ID " + productId + " not found in inventory.");
			return;
		}

		product.removeStock(quantity);
		System.out.println("Stock updated: Removed " + quantity + " units of '" + product.getName() + "'");

		if (product.getStock() < product.getThreshold()) {
			reorderService.triggerReorder(product);
		}

	}

	public void addInventory(int productId, int quantity) {

		if (inventory.isEmpty()) {
			System.out.println("No product registerd yet");
			return;
		}

		Product product = findProductById(productId);

		if (product == null) {
			System.out.println("Product with ID " + productId + " not found in inventory.");
			return;
		}
		product.addStock(quantity);
		System.out.println("Stock updated: Added " + quantity + " units of '" + product.getName() + "'");
	}

	public boolean addProduct(Product newProduct) {
		if (newProduct == null) {
			System.out.println("Cannot add null product to inventory.");
			return false;
		}
		
		boolean nameExists = inventory.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(newProduct.getName()));

        if (nameExists) {
            System.out.println("Error: A product with the name '" + newProduct.getName() + "' already exists.");
            return false;
        }
		inventory.add(newProduct);
		System.out.println("Product added: " + newProduct.getName() + " (ID: " + newProduct.getId() + ")");
		return true;
	}

	public void showInventory() {

		if (inventory.isEmpty()) {
			System.out.println("No product Found ");
			return;
		}

		for (Product product : inventory) {
			System.out.println(product);
			System.out.println("------------------------------------------------");
		}

	}

	private Product findProductById(int productId) {
		for (Product product : inventory) {
			if (product.getId() == productId) {
				return product;
			}
		}
		return null;
	}
}
