package com.ims.model.valuationmodel;

import java.util.List;

import com.ims.model.Product;

public class FIFOValuation implements ValuationStrategy {
	
	@Override
	public double calculate(List<Product> inventory) {
		double valuation = inventory.stream().mapToDouble(product -> product.getPrice() * product.getStock()).sum();
		return valuation;
	}

	

}
