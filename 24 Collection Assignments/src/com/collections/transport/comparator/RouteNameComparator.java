package com.collections.transport.comparator;

import java.util.Comparator;

import com.collections.transport.abstracts.Passenger;

public class RouteNameComparator implements Comparator<Passenger> {

    @Override
    public int compare(Passenger p1, Passenger p2) {
        int routeResult = Integer.compare(p1.getRouteNumber(), p2.getRouteNumber());
        if (routeResult != 0) return routeResult;
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}