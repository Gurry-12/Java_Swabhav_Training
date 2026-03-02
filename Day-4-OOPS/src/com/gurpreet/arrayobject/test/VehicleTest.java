package com.gurpreet.arrayobject.test;

import java.util.Scanner;
import com.gurpreet.arrayobject.models.Vehicle;
import com.gurpreet.arrayobject.models.Car;
import com.gurpreet.arrayobject.models.Truck;
import com.gurpreet.arrayobject.models.Motorcycle;
import com.gurpreet.helpers.Helpers;

/**
 * Test class for Toll Management System using polymorphism
 */
public class VehicleTest {

	private static final int MAX_VEHICLES = 20;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Vehicle[] vehicles = new Vehicle[MAX_VEHICLES];
		int vehicleCount = 0;

		System.out.println("     TOLL MANAGEMENT SYSTEM");
		System.out.println("========================================\n");

		boolean running = true;
		while (running) {
			displayMenu();

			System.out.print("Enter your choice: ");
			int choice = Helpers.validateInt(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				if (vehicleCount > MAX_VEHICLES) {
					System.out.println("Maximum vehicle limit reached.");
					return;
				}
				vehicleCount = processVehicle(scanner, vehicles, vehicleCount);
				break;

			case 2:
				displayAllVehicles(vehicles, vehicleCount);
				break;

			case 3:
				displayTollSummary(vehicles, vehicleCount);
				break;

			case 4:
				searchVehicle(scanner, vehicles, vehicleCount);
				break;

			case 5:
				displayStatistics();
				break;

			case 6:
				running = false;
				System.out.println("Thank you for using Toll Management System.");
				break;

			default:
				System.out.println("Invalid choice. Please enter 1-6.");
			}

			System.out.println();
		}

		scanner.close();
	}

	private static void displayMenu() {
		System.out.println("-----------------------------");
		System.out.println("     Toll Booth Menu");
		System.out.println("1. Process vehicle");
		System.out.println("2. Display all vehicles");
		System.out.println("3. Display toll summary");
		System.out.println("4. Search vehicle");
		System.out.println("5. Display statistics");
		System.out.println("6. Exit");
		System.out.println("-----------------------------");
	}

	private static int processVehicle(Scanner scanner, Vehicle[] vehicles, int count) {
		System.out.println("\nSelect vehicle type:");
		System.out.println("1. Car");
		System.out.println("2. Truck");
		System.out.println("3. Motorcycle");
		System.out.print("Enter choice: ");

		int type = Helpers.validateInt(scanner);
		scanner.nextLine();

		switch (type) {
		case 1:
			vehicles[count] = createCar(scanner);
			break;

		case 2:
			vehicles[count] = createTruck(scanner);
			break;

		case 3:
			vehicles[count] = createMotorcycle(scanner);
			break;

		default:
			System.out.println("Invalid vehicle type.");
			return count;
		}

		// Calculate toll using polymorphism and update static total
		double toll = vehicles[count].calculateToll();
		Vehicle.addToTotalToll(toll);

		System.out.println("\nVehicle processed successfully!");
		System.out.println("Toll charged: $" + toll);

		return count + 1;
	}

	private static Car createCar(Scanner scanner) {
		System.out.print("Enter vehicle number: ");
		String number = Helpers.validateString(scanner);

		System.out.print("Enter owner name: ");
		String owner = Helpers.validateString(scanner);

		System.out.print("Is it a luxury car? (yes/no): ");
		String luxury = Helpers.validateString(scanner);

		return new Car(number, owner, luxury.equalsIgnoreCase("yes"));
	}

	private static Truck createTruck(Scanner scanner) {
		System.out.print("Enter vehicle number: ");
		String number = Helpers.validateString(scanner);

		System.out.print("Enter owner name: ");
		String owner = Helpers.validateString(scanner);

		System.out.print("Enter weight (tons): ");
		double weight = Helpers.validateDouble(scanner);

		System.out.print("Enter number of axles: ");
		int axles = Helpers.validateInt(scanner);
		scanner.nextLine();

		return new Truck(number, owner, weight, axles);
	}

	private static Motorcycle createMotorcycle(Scanner scanner) {
		System.out.print("Enter vehicle number: ");
		String number = Helpers.validateString(scanner);

		System.out.print("Enter owner name: ");
		String owner = Helpers.validateString(scanner);

		System.out.print("Enter engine capacity (CC): ");
		int capacity = Helpers.validateInt(scanner);
		scanner.nextLine();

		return new Motorcycle(number, owner, capacity);
	}

	private static void displayAllVehicles(Vehicle[] vehicles, int count) {
		if (count == 0) {
			System.out.println("No vehicles processed yet.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("       ALL PROCESSED VEHICLES");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			System.out.println("Vehicle #" + (i + 1));
			System.out.println("----------------------------------------");
			vehicles[i].displayDetails();
			System.out.println("----------------------------------------\n");
		}
	}

	private static void displayTollSummary(Vehicle[] vehicles, int count) {
		if (count == 0) {
			System.out.println("No vehicles to calculate toll.");
			return;
		}

		System.out.println("\n========================================");
		System.out.println("         TOLL SUMMARY");
		System.out.println("========================================\n");

		for (int i = 0; i < count; i++) {
			double toll = vehicles[i].calculateToll();
			System.out.println(vehicles[i].getVehicleNumber() + " - " + vehicles[i].getOwnerName() + " - $" + toll);
		}

		System.out.println("\n----------------------------------------");
		System.out.println("Total Vehicles Processed: " + count);
		System.out.println("Total Toll Collected: $" + Vehicle.getTotalTollCollected());
		System.out.println("========================================");
	}

	private static void searchVehicle(Scanner scanner, Vehicle[] vehicles, int count) {
		if (count == 0) {
			System.out.println("No vehicles in the system.");
			return;
		}

		System.out.print("Enter vehicle number to search: ");
		String searchNumber = Helpers.validateString(scanner);

		boolean found = false;
		for (int i = 0; i < count; i++) {
			if (vehicles[i].getVehicleNumber().equalsIgnoreCase(searchNumber)) {
				System.out.println("\nVehicle found:");
				System.out.println("----------------------------------------");
				vehicles[i].displayDetails();
				System.out.println("----------------------------------------");
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Vehicle not found.");
		}
	}

	private static void displayStatistics() {
		System.out.println("\n========================================");
		System.out.println("         TOLL BOOTH STATISTICS");
		System.out.println("========================================");
		System.out.println("Total Vehicles Processed: " + Vehicle.getTotalVehiclesProcessed());
		System.out.println("Total Toll Collected: $" + Vehicle.getTotalTollCollected());

		if (Vehicle.getTotalVehiclesProcessed() > 0) {
			double average = Vehicle.getTotalTollCollected() / Vehicle.getTotalVehiclesProcessed();
			System.out.println("Average Toll per Vehicle: $" + average);
		}

		System.out.println("========================================");
	}

}
