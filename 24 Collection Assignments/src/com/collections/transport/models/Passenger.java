package com.collections.transport.models;

import java.util.Objects;

public abstract class Passenger implements Comparable<Passenger> {

    private String id;
    private String name;
    private int routeNumber;

    public Passenger(String name, int routeNumber, String prefix, long counter)
            throws InvalidPassengerException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidPassengerException("Passenger name can't be empty");
        }
        if (routeNumber <= 0) {
            throw new InvalidPassengerException("Route number must be greater than 0");
        }

        this.id = prefix + counter;
        this.name = name;
        this.routeNumber = routeNumber;
    }

    public String getId()         { return id; }
    public String getName()       { return name; }
    public int getRouteNumber()   { return routeNumber; }

    // Natural ordering: alphabetical by name
    @Override
    public int compareTo(Passenger other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // Duplicate: same name + same route number
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Passenger)) return false;
        Passenger other = (Passenger) obj;
        return other.name.equalsIgnoreCase(name)
            && other.routeNumber == routeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), routeNumber);
    }

    public abstract void printDetails();
}