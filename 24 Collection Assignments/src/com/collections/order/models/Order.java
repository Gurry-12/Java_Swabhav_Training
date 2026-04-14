package com.collections.order.models;

import java.util.Objects;

public abstract class Order {

    private String id;
    private String customerName;
    private String itemDescription;
    private double totalAmount;
    private OrderStatus status;

    public Order(String customerName, String itemDescription, double totalAmount,
                 OrderStatus status, String prefix, long counter) throws InvalidOrderException {

        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidOrderException("Customer name can't be empty");
        }
        if (itemDescription == null || itemDescription.trim().isEmpty()) {
            throw new InvalidOrderException("Item description can't be empty");
        }
        if (totalAmount <= 0) {
            throw new InvalidOrderException("Total amount must be greater than 0");
        }
        if (status == null) {
            throw new InvalidOrderException("Status can't be null");
        }

        this.id = prefix + counter;
        this.customerName = customerName;
        this.itemDescription = itemDescription;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getId()              { return id; }
    public String getCustomerName()    { return customerName; }
    public String getItemDescription() { return itemDescription; }
    public double getTotalAmount()     { return totalAmount; }
    public OrderStatus getStatus()     { return status; }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Two orders are duplicate if same customer places order for same item
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order other = (Order) obj;
        return other.customerName.equalsIgnoreCase(customerName)
            && other.itemDescription.equalsIgnoreCase(itemDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName.toLowerCase(), itemDescription.toLowerCase());
    }

    public abstract void printDetails();
}