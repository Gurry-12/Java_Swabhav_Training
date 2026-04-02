package com.ims.model;

public class Product {

	private int id;
	private String name;
	private ProductType productType;
	private int threshold;
	private int stock;
	private double price;

	public Product(int id, String name, ProductType productType, int threshold, double price) {
		this.id = id;
		this.name = name;
		this.productType = productType;
		this.threshold = threshold;
		this.price = price;
		this.stock = 0;
	}
	

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public int getThreshold() {
		return threshold;
	}

	public int getStock() {
		return stock;
	}

	public double getPrice() {
		return price;
	}

	public void addStock(int quantity) {
		stock += quantity;
	}

	public void removeStock(int quantity) {
		if (quantity > stock) {
			System.out.println("quantity can not be more than stock");
			return;
		}

		stock -= quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Id : %-2d | Name : %-2s | Type : %-2s | Threshold : %-2d | Stock : %-2d | Price %-2.2f",
				id, name, productType.toString(), threshold, stock, price);
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product other)) return false;
        return this.id == other.id && this.name.equalsIgnoreCase(other.name);           
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);          
    }

}
