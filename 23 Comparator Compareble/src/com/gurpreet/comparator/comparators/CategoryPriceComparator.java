package com.gurpreet.comparator.comparators;

import java.util.Comparator;

import com.gurpreet.comparator.models.Product;

public class CategoryPriceComparator implements Comparator<Product>{

	@Override
	public int compare(Product product1, Product product2) {
		
		int categoryResult = getCategoryOfProduct(product1).compareTo( getCategoryOfProduct(product2));
		
		if(categoryResult != 0) {
			return categoryResult;
		}
		
		return Double.compare(getPriceOfProduct(product1), getPriceOfProduct(product2));
	}
	
	private String getCategoryOfProduct(Product product) {
		return product.getCategory();
	}
	
	private double getPriceOfProduct(Product product) {
		return product.getPrice();
	}

}
