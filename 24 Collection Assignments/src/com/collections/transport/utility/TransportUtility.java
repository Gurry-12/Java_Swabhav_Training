package com.collections.transport.utility;

import java.util.Scanner;

import com.collections.transport.enums.RouteType;

public class TransportUtility {

    public static void displayPassengerTypes() {
        System.out.println("Select Passenger Type:");
        System.out.println("1. Daily Passenger");
        System.out.println("2. Tourist Passenger");
    }

    public static RouteType printAndGetRouteType(Scanner scanner) {
        RouteType[] types = RouteType.values();
        System.out.println("Select Route Type:");
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " : " + types[i]);
        }
        int choice = Helpers.validateIntRange(scanner, 1, types.length);
        return types[choice - 1];
    }

    public static void displayMenu() {
        System.out.println("\n====== Transport Management System ======");
        System.out.println("1. Register Passenger");
        System.out.println("2. View Passenger by ID");
        System.out.println("3. View All Passengers (sorted by name)");
        System.out.println("4. View All Passengers (sorted by route)");
        System.out.println("5. Board Next Waiting Passenger");
        System.out.println("6. View Passengers by Route Number");
        System.out.println("7. Remove Passenger");
        System.out.println("8. Exit");
        System.out.println("=========================================");
    }
}