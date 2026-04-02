package com.ims.model.valuationmodel;

import java.util.List;

import com.ims.model.Product;

public interface ValuationStrategy {

	double calculate(List<Product> inventory);
}
