package com.ims.model.inventorymodel;

import java.util.Scanner;

import com.ims.model.Product;
import com.ims.model.ProductType;

public class InventoryController {

    private static int next = 1;

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
                	inventoryService.showInventory();
                	break;
                	
                case 6  :
                {
                    isRun = false;
                    renderer.showMessage("Thank you for visiting");
                    break;
                }
                default:
                	renderer.showMessage("Please enter valid choice.");
            }
        }
    }

    private void calculateValuation() {
        double value = inventoryService.calculateValuation();
        renderer.showValuation(value);
    }

    private void removeStock(Scanner scanner) {
        renderer.showMessage("Enter Product Id.");
        int id = Helpers.validateIntNonNegative(scanner);
        renderer.showMessage("Enter Quantity");
        int quantity = Helpers.validateIntNonNegative(scanner);
        inventoryService.removeInventory(id, quantity);
    }

    private void addStock(Scanner scanner) {
        renderer.showMessage("Enter Product Id.");
        int id = Helpers.validateIntNonNegative(scanner);
        renderer.showMessage("Enter Quantity");
        int quantity = Helpers.validateIntNonNegative(scanner);
        inventoryService.addInventory(id, quantity);
    }

    private void addProduct(Scanner scanner) {
        renderer.showMessage("\nPlease add the required fields for adding new product.");
        int id = next++;
        renderer.showMessage("Enter Name");
        String name = Helpers.validateStringLettersOnly(scanner);
        ProductType type = getProductType(scanner);
        renderer.showMessage("Enter threshold");
        int threshold = Helpers.validateIntPositive(scanner);
        renderer.showMessage("Enter Price per unit");
        double price = Helpers.validateDoubleNonNegative(scanner);

        Product newProduct = new Product(id, name, type, threshold, price);
        boolean added = inventoryService.addProduct(newProduct);
        if (added) {
            renderer.showMessage("Product Added.");
        }
    }

    
    private ProductType getProductType(Scanner scanner) {
        ProductType[] types = ProductType.values();
        renderer.showMessage("Select Product Type:");
        for (int i = 0; i < types.length; i++) {
            renderer.showMessage((i + 1) + " : " + types[i]);
        }
        int choice = Helpers.validateIntRange(scanner, 1, types.length);
        return types[choice - 1];
    }
}