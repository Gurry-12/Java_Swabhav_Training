package com.ims.model.inventorymodel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.ims.model.Product;
import com.ims.model.ProductType;
import com.ims.model.exception.DuplicateProductException;
import com.ims.model.exception.InsufficientStockException;
import com.ims.model.exception.ProductNotFoundException;
import com.ims.model.valuationmodel.ValuationStrategy;

public class InventoryService {

	private final ReorderService reorderService;
	private ValuationStrategy valuationStrategy;
	private final List<Product> inventory = new LinkedList<>();

	public InventoryService(ReorderService reorderService, ValuationStrategy valuationStrategy) {
		this.reorderService = reorderService;
		this.valuationStrategy = valuationStrategy;
	}

	public void setValuationStrategy(ValuationStrategy strategy) {
		this.valuationStrategy = strategy;
	}

	public String getValuationStrategyName() {
		return valuationStrategy.getClass().getSimpleName();
	}

	public Product addProduct(String name, ProductType type, int threshold, int stock, double price)
			throws DuplicateProductException {
		String normalizedName = name.trim();

		boolean nameExists = inventory.stream().anyMatch(p -> p.getName().equalsIgnoreCase(normalizedName));

		if (nameExists) {
			throw new DuplicateProductException(normalizedName);
		}

		Product product = new Product(normalizedName, type, threshold, stock, price);
		inventory.add(product);
		return product;
	}

	public void addInventory(int productId, int quantity) throws ProductNotFoundException {
		Product product = findProductById(productId);
		product.addStock(quantity);
	}

	public void removeInventory(int productId, int quantity)
			throws ProductNotFoundException, InsufficientStockException {
		Product product = findProductById(productId);
		product.removeStock(quantity);

		if (product.getStock() < product.getThreshold()) {
			reorderService.triggerReorder(product);
		}
	}

	public double calculateValuation() {
		return valuationStrategy.calculate(inventory);
	}

	public List<Product> getInventory() {
		return Collections.unmodifiableList(inventory);
	}

	private Product findProductById(int productId) throws ProductNotFoundException {
		return inventory.stream().filter(p -> p.getId() == productId).findFirst()
				.orElseThrow(() -> new ProductNotFoundException(productId));
	}
}