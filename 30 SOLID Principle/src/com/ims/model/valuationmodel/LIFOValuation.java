package com.ims.model.valuationmodel;

import java.util.List;

import com.ims.model.Product;
import com.ims.model.ProductType;

public class LIFOValuation implements ValuationStrategy {

	@Override
	public double calculate(List<Product> inventory) {

		double valuation = inventory.stream()
				.filter(product -> product.getProductType().equals(ProductType.NONPERISHABLE))
				.mapToDouble(product -> product.getPrice() * product.getStock()).sum();
		return valuation;
	}

}
