package com.collections.transport.models;

import com.collections.transport.abstracts.Passenger;
import com.collections.transport.exceptions.InvalidPassengerException;

public class TouristPassenger extends Passenger {

    private static long counter = 5000;
    private static final String PREFIX = "TP";

    private String destination;
    private int tripDays;

    public TouristPassenger(String name, int routeNumber, String destination, int tripDays)
            throws InvalidPassengerException {

        super(name, routeNumber, PREFIX, counter++);

        if (destination == null || destination.trim().isEmpty()) {
            throw new InvalidPassengerException("Destination can't be empty");
        }
        if (tripDays <= 0) {
            throw new InvalidPassengerException("Trip days must be greater than 0");
        }

        this.destination = destination;
        this.tripDays = tripDays;
    }

    @Override
    public void printDetails() {
        System.out.println(" Passenger ID  : " + getId());
        System.out.println(" Type          : Tourist Passenger");
        System.out.println(" Name          : " + getName());
        System.out.println(" Route Number  : " + getRouteNumber());
        System.out.println(" Destination   : " + destination);
        System.out.println(" Trip Days     : " + tripDays);
    }
}