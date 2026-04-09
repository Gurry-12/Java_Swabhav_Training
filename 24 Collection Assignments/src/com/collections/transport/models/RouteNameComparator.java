package com.collections.transport.models;

import java.util.Comparator;

public class RouteNameComparator implements Comparator<Passenger> {

    @Override
    public int compare(Passenger p1, Passenger p2) {
        int routeResult = Integer.compare(p1.getRouteNumber(), p2.getRouteNumber());
        if (routeResult != 0) return routeResult;
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}