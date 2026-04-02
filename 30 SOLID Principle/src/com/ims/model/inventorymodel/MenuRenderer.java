package com.ims.model.inventorymodel;

public class MenuRenderer {

    public void showHeader() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("           Inventory Management System               ");
        System.out.println("-----------------------------------------------------------\n");
    }

    public void showMenu() {
        System.out.println("\n                Menu                  ");
        System.out.println("1. Add Product                          ");
        System.out.println("2. Add Stock to Existing Product        ");
        System.out.println("3. Remove Stock                         ");
        System.out.println("4. Calculate Valuation                  ");
        System.out.println("5. List Products                        ");
        System.out.println("6. Exit                                 ");
    }

    public void showValuation(double value) {
        System.out.println("Total inventory value (using FIFO): " + value);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}