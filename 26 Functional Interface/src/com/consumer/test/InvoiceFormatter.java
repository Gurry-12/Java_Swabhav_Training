package com.consumer.test;

import java.util.function.Consumer;

import com.consumer.models.Invoice;

public class InvoiceFormatter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Consumer<Invoice> printInvoice = invoice -> System.out
				.println(invoice.getItemName() + "\t" + invoice.getQuantity() + "\t" + invoice.getPrice());

		Invoice inv1 = new Invoice("Pen", 2, 10.0);
		Invoice inv2 = new Invoice("Notebook", 3, 50.0);
		Invoice inv3 = new Invoice("Eraser", 5, 5.0);

		System.out.println("Invoice Bill Details");
		System.out.println("=====================================");

		// Using the Consumer
		printInvoice.accept(inv1);
		printInvoice.accept(inv2);
		printInvoice.accept(inv3);

	}

}
