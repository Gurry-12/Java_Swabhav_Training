package com.gurpreet.oopsassignment.test;

import java.util.Scanner;

import com.gurpreet.oopsassignment.enums.EngineType;
import com.gurpreet.oopsassignment.enums.OilType;
import com.gurpreet.oopsassignment.helpers.Helpers;
import com.gurpreet.oopsassignment.models.Bike;
import com.gurpreet.oopsassignment.models.Car;
import com.gurpreet.oopsassignment.models.Vehicle;

;

public class VehicleTest {

    private static final int MAX_VEHICLES = 8;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Vehicle[] vehicles = new Vehicle[MAX_VEHICLES];
        int vehicleCount = 0;

        System.out.println("=====================================");
        System.out.println("     Vehicle Registration System");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {

            displayMainMenu();

            System.out.print("Enter your choice (1-4): ");
            int choice = Helpers.validateIntRange(scanner, 1, 4);

            switch (choice) {
                case 1:
                    if (vehicleCount >= MAX_VEHICLES) {
                        System.out.println("\nMaximum vehicle limit reached (" + MAX_VEHICLES + ").");
                        break;
                    }
                    vehicleCount = registerVehicle(scanner, vehicles, vehicleCount);
                    break;

                case 2:
                    displayAllVehicles(vehicles, vehicleCount);
                    break;

                case 3:
                    System.out.println("\nTotal vehicles registered: " + vehicleCount);
                    break;

                case 4:
                    running = false;
                    System.out.println("\nThank you for using the system. Goodbye.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Register new vehicle");
        System.out.println("2. Display all registered vehicles");
        System.out.println("3. Show total number of vehicles");
        System.out.println("4. Exit");
    }

    private static int registerVehicle(Scanner scanner, Vehicle[] vehicles, int currentCount) {
        System.out.println("\n--- Register New Vehicle ---");


        System.out.print("Enter owner name: ");
        String owner = Helpers.validateStringLettersOnly(scanner);

        System.out.print("Enter base usage charge: ");
        double charge = Helpers.validateDoublePositive(scanner);

        System.out.println("\nSelect vehicle type:");
        System.out.println("  1) Car");
        System.out.println("  2) Bike");
        int type = Helpers.validateIntRange(scanner, 1, 2);

        Vehicle newVehicle = null;
        
        switch(type) {
        case 1: {
            System.out.println("\nSelect fuel type:");
            System.out.println("  1) PETROL");
            System.out.println("  2) DIESEL");
            int fuelChoice = Helpers.validateIntRange(scanner, 1, 2);
            OilType oil = (fuelChoice == 1) ? OilType.PETROL : OilType.DIESEL;

            System.out.print("Enter vehicle color: ");
            String color = Helpers.validateStringNonEmpty(scanner);

            newVehicle = new Car(owner, charge, oil, color);
            break;
        } 
        case 2: {
            System.out.println("\nSelect engine capacity:");
            EngineType[] capacities = EngineType.values();
            for (int i = 0; i < capacities.length; i++) {
                System.out.printf("  %d) %s%n", i + 1, capacities[i]);
            }
            int ccChoice = Helpers.validateIntRange(scanner, 1, capacities.length) - 1;

            newVehicle = new Bike(owner, charge, capacities[ccChoice]);
            break;
        }
        
        default:
        	System.out.println("Enter valid input");
        	
        }

        vehicles[currentCount] = newVehicle;
        System.out.println("\nVehicle registered successfully.");
        newVehicle.processDetails();
        return currentCount + 1;
    }

    private static void displayAllVehicles(Vehicle[] vehicles, int count) {
        if (count == 0) {
            System.out.println("\nNo vehicles registered yet.");
            return;
        }

        System.out.println("\n=== Registered Vehicles ===");
        for (int i = 0; i < count; i++) {
            vehicles[i].processDetails();
            System.out.println("----------------------------------------");
        }
    }
}