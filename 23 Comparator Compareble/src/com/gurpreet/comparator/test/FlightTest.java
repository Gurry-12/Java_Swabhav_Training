package com.gurpreet.comparator.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.comparator.models.Flight;

public class FlightTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Flight> flights = new ArrayList<>();

        System.out.println("=====================================");
        System.out.println("            Flight List ");
        System.out.println("=====================================");

        int n = getPositiveInteger(scanner, "How many flights do you want to add? ");

        for (int i = 1; i <= n; i++) {
            System.out.println("\nFlight #" + i + ":");
            String airline = getNonEmptyString(scanner, "Enter airline name: ");
            double fare = getPositiveDouble(scanner, "Enter fare (in ₹): ");
            flights.add(new Flight(airline, fare));
        }

        System.out.println("\nBefore sorting:");
        printFlights(flights);

        Collections.sort(flights);

        System.out.println("\nAfter sorting (by fare ascending):");
        printFlights(flights);

        scanner.close();
    }

    private static int getPositiveInteger(Scanner scanner, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                scanner.next();
            }
            value = scanner.nextInt();
            scanner.nextLine();
        } while (value <= 0);
        return value;
    }

    private static double getPositiveDouble(Scanner scanner, String prompt) {
        double value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.print("Enter a valid amount: ");
                scanner.next();
            }
            value = scanner.nextDouble();
            scanner.nextLine();
            if (value <= 0) System.out.println("Fare must be positive.");
        } while (value <= 0);
        return value;
    }

    private static String getNonEmptyString(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) System.out.println("Airline name cannot be empty.");
        } while (input.isEmpty());
        return input;
    }

    private static void printFlights(List<Flight> list) {
        System.out.println("Airline          Fare (₹)");
        System.out.println("-----------------------------");
        for (Flight f : list) {
            System.out.printf("%-16s %,10.0f%n", f.getAirline(), f.getFare());
        }
    }
}