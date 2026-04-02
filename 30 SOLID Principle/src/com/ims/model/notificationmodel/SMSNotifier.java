package com.ims.model.notificationmodel;

import com.ims.model.Product;

public class SMSNotifier implements Notifier {

	@Override
	public void notify(Product product) {
		// TODO Auto-generated method stub
		System.out.println("[SMS] Notification sent: Low stock alert for '" + product.getName() + "'");
	}

}
