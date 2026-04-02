package com.ims.model.notificationmodel;

import com.ims.model.Product;

public class EmailNotifier implements Notifier {

	@Override
	public void notify(Product product) {
		// TODO Auto-generated method stub
		System.out.println("[EMAIL] Notification sent: Low stock alert for '" + product.getName() + "'");
	}

}
