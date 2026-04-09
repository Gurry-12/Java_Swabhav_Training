package com.collections.order.models;

import java.util.Comparator;

public class CustomerAmountComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        int nameResult = o1.getCustomerName().compareToIgnoreCase(o2.getCustomerName());
        if (nameResult != 0) {
            return nameResult;
        }
        return Double.compare(o1.getTotalAmount(), o2.getTotalAmount());
    }
}