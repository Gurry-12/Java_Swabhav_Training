package com.collections.order.models;

public class PriorityOrder extends Order {

    private static long counter = 2000;
    private static final String PREFIX = "PO";

    private PriorityLevel priorityLevel;

    public PriorityOrder(String customerName, String itemDescription,
                         double totalAmount, PriorityLevel priorityLevel) throws InvalidOrderException {

        super(customerName, itemDescription, totalAmount, OrderStatus.PENDING, PREFIX, counter++);

        if (priorityLevel == null) {
            throw new InvalidOrderException("Priority level can't be null");
        }

        this.priorityLevel = priorityLevel;
    }

    public String getPriorityLevel() { return priorityLevel.toString(); }

    @Override
    public void printDetails() {
        System.out.println(" Order ID        : " + getId());
        System.out.println(" Type            : Priority Order");
        System.out.println(" Customer Name   : " + getCustomerName());
        System.out.println(" Item            : " + getItemDescription());
        System.out.println(" Total Amount    : Rs. " + getTotalAmount());
        System.out.println(" Priority Level  : " + getPriorityLevel());
        System.out.println(" Status          : " + getStatus());
    }
}