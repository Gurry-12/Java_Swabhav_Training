package com.collections.inventory.abstracts;

import java.util.Objects;

import com.collections.inventory.enums.Category;
import com.collections.inventory.enums.ProductStatus;
import com.collections.inventory.exceptions.InvalidProductException;

public abstract class Product implements Comparable<Product> {

    private String id;
    private String name;
    private Category category;
    private double price;
    private ProductStatus status = ProductStatus.AVAILABLE;

   
    public Product(String name, Category category, double price, String prefix, long counter)
            throws InvalidProductException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductException("Product name can't be empty");
        }
        if (category == null) {
            throw new InvalidProductException("Category can't be null");
        }
        if (price <= 0) {
            throw new InvalidProductException("Price must be greater than 0");
        }

        this.id = prefix + counter;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId()          { return id; }
    public String getName()        { return name; }
    public String getCategory()    { return category.toString(); }
    public double getPrice()       { return price; }
    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status)   { this.status = status; }

    // Natural ordering: alphabetical by name
    @Override
    public int compareTo(Product other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // Duplicate: same name + same category
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return other.name.equalsIgnoreCase(name)
            && other.category.equals(category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), category);
    }

    public abstract void printDetails();
}