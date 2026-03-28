package com.collections.transport.models;

import com.collections.transport.abstracts.Passenger;
import com.collections.transport.enums.RouteType;
import com.collections.transport.exceptions.InvalidPassengerException;

public class DailyPassenger extends Passenger {

    private static long counter = 1000;
    private static final String PREFIX = "DP";

    private RouteType routeType;
    private int monthlyPass; // pass number

    public DailyPassenger(String name, int routeNumber, RouteType routeType, int monthlyPass)
            throws InvalidPassengerException {

        super(name, routeNumber, PREFIX, counter++);

        if (routeType == null) {
            throw new InvalidPassengerException("Route type can't be null");
        }
        if (monthlyPass <= 0) {
            throw new InvalidPassengerException("Monthly pass number must be positive");
        }

        this.routeType = routeType;
        this.monthlyPass = monthlyPass;
    }

    @Override
    public void printDetails() {
        System.out.println(" Passenger ID  : " + getId());
        System.out.println(" Type          : Daily Passenger");
        System.out.println(" Name          : " + getName());
        System.out.println(" Route Number  : " + getRouteNumber());
        System.out.println(" Route Type    : " + routeType);
        System.out.println(" Monthly Pass  : " + monthlyPass);
    }
}