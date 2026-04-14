package com.collections.transport.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class TransportManager {

    private Set<Passenger> passengers;                       // HashSet — no duplicates
    private Queue<Passenger> boardingQueue;                  // LinkedList — waiting to board
    private Map<Integer, List<Passenger>> routeMap;          // HashMap — route-wise passengers
    private Scanner scanner;

    public TransportManager(Scanner scanner) {
        this.passengers = new HashSet<>();
        this.boardingQueue = new LinkedList<>();
        this.routeMap = new HashMap<>();
        this.scanner = scanner;
    }

    public void registerPassenger() throws InvalidPassengerException {
        TransportUtility.displayPassengerTypes();
        int choice = Helpers.validateIntRange(scanner, 1, 2);

        Passenger passenger;
        switch (choice) {
            case 1:
                passenger = createDailyPassenger();
                break;
            case 2:
                passenger = createTouristPassenger();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (passengers.contains(passenger)) {
            throw new InvalidPassengerException(
                "Passenger already registered with same name and route.");
        }

        passengers.add(passenger);
        boardingQueue.add(passenger);

        // Route-wise grouping
        int routeKey = passenger.getRouteNumber();
        if (!routeMap.containsKey(routeKey)) {
            routeMap.put(routeKey, new ArrayList<Passenger>());
        }
        routeMap.get(routeKey).add(passenger);

        System.out.println("Passenger registered! ID: " + passenger.getId());
    }

    private Passenger createDailyPassenger() throws InvalidPassengerException {
        System.out.println("Enter Passenger Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Route Number:");
        int route = Helpers.validateIntRange(scanner, 1, 999);

        RouteType routeType = TransportUtility.printAndGetRouteType(scanner);

        System.out.println("Enter Monthly Pass Number:");
        int pass = Helpers.validateIntRange(scanner, 1, 999999);

        return new DailyPassenger(name, route, routeType, pass);
    }

    private Passenger createTouristPassenger() throws InvalidPassengerException {
        System.out.println("Enter Passenger Name:");
        String name = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Route Number:");
        int route = Helpers.validateIntRange(scanner, 1, 999);

        System.out.println("Enter Destination:");
        String destination = Helpers.validateStringNonEmpty(scanner);

        System.out.println("Enter Trip Days:");
        int days = Helpers.validateIntRange(scanner, 1, 365);

        return new TouristPassenger(name, route, destination, days);
    }

    public void viewPassengerById(String passengerId) throws InvalidPassengerException {
        findById(passengerId).printDetails();
    }

    public void viewAllByName() throws InvalidPassengerException {
        if (passengers.isEmpty()) {
            throw new InvalidPassengerException("No passengers registered.");
        }
        ArrayList<Passenger> sorted = new ArrayList<>(passengers);
        Collections.sort(sorted); // natural: by name

        int count = 0;
        for (Passenger p : sorted) {
            System.out.println("\nPassenger " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    public void viewAllByRoute() throws InvalidPassengerException {
        if (passengers.isEmpty()) {
            throw new InvalidPassengerException("No passengers registered.");
        }
        ArrayList<Passenger> sorted = new ArrayList<>(passengers);
        Collections.sort(sorted, new RouteNameComparator()); // custom: by route then name

        int count = 0;
        for (Passenger p : sorted) {
            System.out.println("\nPassenger " + (++count) + ":");
            p.printDetails();
            System.out.println("============================");
        }
    }

    public void boardNextPassenger() {
        if (boardingQueue.isEmpty()) {
            System.out.println("No passengers waiting to board.");
            return;
        }
        Passenger next = boardingQueue.poll();
        System.out.println("Boarding next passenger:");
        next.printDetails();
    }

    public void viewByRoute(int routeNumber) throws InvalidPassengerException {
        List<Passenger> routePassengers = routeMap.get(routeNumber);

        if (routePassengers == null || routePassengers.isEmpty()) {
            throw new InvalidPassengerException("No passengers found for route: " + routeNumber);
        }

        System.out.println("Passengers on Route " + routeNumber + ":");
        int count = 0;
        for (Passenger p : routePassengers) {
            System.out.println("\nPassenger " + (++count) + ":");
            p.printDetails();
            System.out.println("----------------------------");
        }
    }

    // Iterator-based safe removal of a specific passenger
    public void removePassenger(String passengerId) throws InvalidPassengerException {
        Iterator<Passenger> iterator = passengers.iterator();
        Passenger toRemove = null;

        while (iterator.hasNext()) {
            Passenger p = iterator.next();
            if (p.getId().equals(passengerId)) {
                toRemove = p;
                iterator.remove();
                break;
            }
        }

        if (toRemove == null) {
            throw new InvalidPassengerException("Passenger not found with ID: " + passengerId);
        }

        // Clean up route map
        List<Passenger> routeList = routeMap.get(toRemove.getRouteNumber());
        if (routeList != null) {
            routeList.remove(toRemove);
        }

        System.out.println("Passenger removed successfully:");
        System.out.println(" Name : " + toRemove.getName());
        System.out.println(" ID   : " + toRemove.getId());
    }

    private Passenger findById(String passengerId) throws InvalidPassengerException {
        for (Passenger p : passengers) {
            if (p.getId().equals(passengerId)) {
                return p;
            }
        }
        throw new InvalidPassengerException("Passenger not found with ID: " + passengerId);
    }
}