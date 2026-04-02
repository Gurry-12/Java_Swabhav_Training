package com.ims.model.services;

import java.util.List;

import com.ims.model.Product;
import com.ims.model.notificationmodel.Notifier;

public class ReorderService {

	private List<Notifier> notifiers;

	public ReorderService(List<Notifier> notifiers) {
		super();
		this.notifiers = notifiers;
	}

	public void triggerReorder(Product product) {
		
		int reorderQuantity = product.getThreshold() * 2;
		System.out.println("Reorder threshold reached for '" + product.getName() + "'. Triggering reorder...");
		System.out.println("Reorder placed for " + reorderQuantity + " units of '" + product.getName() + "'");
		for (Notifier notifier : notifiers) {
			notifier.notify(product);
		}
	}

}
