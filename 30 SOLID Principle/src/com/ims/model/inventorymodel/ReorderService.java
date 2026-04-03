package com.ims.model.inventorymodel;

import java.util.List;

import com.ims.model.Product;
import com.ims.model.notificationmodel.Notifier;

public class ReorderService {

	private List<Notifier> notifiers;
	private static final int REORDER_MULTYPLIER = 2;
	
	public ReorderService(List<Notifier> notifiers) {
		super();
		this.notifiers = notifiers;
	}

	public void triggerReorder(Product product) {
		
		int reorderQuantity = product.getThreshold() * REORDER_MULTYPLIER;
		System.out.println("Reorder threshold reached for '" + product.getName() + "'. Triggering reorder...");
		System.out.println("Reorder placed for " + reorderQuantity + " units of '" + product.getName() + "'");
		for (Notifier notifier : notifiers) {
			notifier.notify(product);
		}
	}

}
