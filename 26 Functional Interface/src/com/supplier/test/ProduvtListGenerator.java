package com.supplier.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ProduvtListGenerator {

	
	public static void main(String[] args) {
		
		Supplier<List<String>> productList = () -> Arrays.asList(
				"Leptop",
				"Smartphone",
                "Headphones",
                "Tablet",
                "Smart Watch"
                );
		
		System.out.println("Default Product List:\n");

        List<String> products = productList.get();

        for (String product : products) {
            System.out.println(product);
        }
	}
}
