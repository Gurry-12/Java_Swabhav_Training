package com.collections.transport.test;

import java.util.Scanner;

import com.collections.transport.exceptions.InvalidPassengerException;
import com.collections.transport.models.TransportManager;
import com.collections.transport.utility.Helpers;
import com.collections.transport.utility.TransportUtility;

public class TransportManagementTest {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            TransportManager manager = new TransportManager(scanner);

            System.out.println("-----------------------------------");
            System.out.println("  Transport Route & Passenger System");
            System.out.println("-----------------------------------\n");

            boolean isRun = true;
            while (isRun) {
                try {
                    TransportUtility.displayMenu();
                    int choice = Helpers.validateIntRange(scanner, 1, 8);

                    switch (choice) {

                        case 1:
                            manager.registerPassenger();
                            break;

                        case 2:
                            String passengerId = Helpers.validatePassengerId(scanner);
                            manager.viewPassengerById(passengerId);
                            break;

                        case 3:
                            manager.viewAllByName();
                            break;

                        case 4:
                            manager.viewAllByRoute();
                            break;

                        case 5:
                            manager.boardNextPassenger();
                            break;

                        case 6:
                            System.out.println("Enter Route Number:");
                            int route = Helpers.validateIntRange(scanner, 1, 999);
                            manager.viewByRoute(route);
                            break;

                        case 7:
                            passengerId = Helpers.validatePassengerId(scanner);
                            manager.removePassenger(passengerId);
                            break;

                        case 8:
                            isRun = false;
                            System.out.println("Goodbye!");
                            break;

                        default:
                            System.out.println("Enter valid input.");
                    }

                } catch (InvalidPassengerException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}