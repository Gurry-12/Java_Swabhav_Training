package com.collections.order.comparator;

import java.util.Comparator;

import com.collections.order.abstracts.Order;

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