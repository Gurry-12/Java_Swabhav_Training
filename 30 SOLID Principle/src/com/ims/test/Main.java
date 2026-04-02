package com.ims.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ims.model.inventorymodel.InventoryController;
import com.ims.model.inventorymodel.InventoryService;
import com.ims.model.inventorymodel.MenuRenderer;
import com.ims.model.inventorymodel.ReorderService;
import com.ims.model.notificationmodel.EmailNotifier;
import com.ims.model.notificationmodel.Notifier;
import com.ims.model.notificationmodel.SMSNotifier;
import com.ims.model.valuationmodel.FIFOValuation;
import com.ims.model.valuationmodel.ValuationStrategy;

public class Main {

    public static void main(String[] args) {

        // wiring — composition root
        List<Notifier> notifiers = new ArrayList<>();
        notifiers.add(new EmailNotifier());
        notifiers.add(new SMSNotifier());

        ReorderService reorderService = new ReorderService(notifiers);
        ValuationStrategy strategy = new FIFOValuation();
        InventoryService inventoryService = new InventoryService(reorderService, strategy);
        MenuRenderer renderer = new MenuRenderer();

        Scanner scanner = new Scanner(System.in);
        InventoryController controller = new InventoryController(inventoryService, renderer);
        controller.start(scanner);
        scanner.close();
    }
}