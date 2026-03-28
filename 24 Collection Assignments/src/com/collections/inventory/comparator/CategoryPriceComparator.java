package com.collections.inventory.comparator;

import java.util.Comparator;

import com.collections.inventory.abstracts.Product;

public class CategoryPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        int categoryResult = p1.getCategory().compareTo(p2.getCategory());
        if (categoryResult != 0) return categoryResult;
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}