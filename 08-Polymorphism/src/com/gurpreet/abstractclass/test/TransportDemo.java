package com.gurpreet.abstractclass.test;

import java.util.Scanner;

import com.gurpreet.abstractclass.models.Bus;
import com.gurpreet.abstractclass.models.Metro;
import com.gurpreet.abstractclass.models.Taxi;
import com.gurpreet.abstractclass.models.Transport;
import com.gurpreet.helpers.Helpers;

public class TransportDemo {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int transportCount = 0;

		System.out.println("-----------------------------------");
		System.out.println(" Welcome to Transport Fare Calculation Engine ");
		System.out.println();

		System.out.print("Enter maximum number of transports you want to add: ");
		int maxTransports = Helpers.validateInt(scanner);

		if (maxTransports <= 0) {
			System.out.println("Maximum number must be positive. Exiting.");
			scanner.close();
			return;
		}

		Transport[] transports = new Transport[maxTransports];

		boolean running = true;

		while (running) {

			displayDashboard();

			System.out.print("Choose an option: ");
			int choice = Helpers.validateInt(scanner);

			switch (choice) {

			case 1:
				if (transportCount >= maxTransports) {
					System.out.println("Maximum number of transports reached (" + maxTransports + ").");
					break;
				}
				addBus(scanner, transports, transportCount);
				transportCount++;
				break;
			case 2:
				if (transportCount >= maxTransports) {
					System.out.println("Maximum number of transports reached (" + maxTransports + ").");
					break;
				}
				addMetro(scanner, transports, transportCount);
				transportCount++;
				break;
			case 3:
				if (transportCount >= maxTransports) {
					System.out.println("Maximum number of transports reached (" + maxTransports + ").");
					break;
				}
				addTaxi(scanner, transports, transportCount);
				transportCount++;
				break;

			case 4:
				printAllTickets(transports, transportCount);
				break;

			case 5:
				running = false;
				System.out.println("Thank you for using the Transport Fare Calculation Engine.");
				break;

			default:
				System.out.println("Please enter a valid choice (1-5).");
			}
		}

		scanner.close();
	}

	private static void displayDashboard() {
		System.out.println("\n-----------------------------");
		System.out.println("1. Add Bus");
		System.out.println("2. Add Metro");
		System.out.println("3. Add Taxi");
		System.out.println("4. Calculate All Fares and Print Tickets");
		System.out.println("5. Exit");
		System.out.println("-----------------------------");
	}

	private static void addBus(Scanner scanner, Transport[] transports, int index) {

		System.out.print("Enter Base Fare: ");
		double baseFare = Helpers.validateDouble(scanner);

		System.out.print("Enter Distance (in km): ");
		double distance = Helpers.validateDouble(scanner);

		if (baseFare < 0 || distance <= 0) {
			System.out.println("Invalid values. Base fare >= 0 and distance > 0 required. Not added.");
			return;
		}

		transports[index] = new Bus(baseFare, distance);
		System.out.println("Bus transport added successfully.");
	}

	private static void addMetro(Scanner scanner, Transport[] transports, int index) {
		System.out.print("Enter Base Fare: ");
		double baseFare = Helpers.validateDouble(scanner);

		System.out.print("Enter Number of Stations: ");
		int stations = Helpers.validateInt(scanner);

		if (baseFare < 0 || stations <= 0) {
			System.out.println("Invalid values. Base fare >= 0 and stations > 0 required. Not added.");
			return;
		}

		transports[index] = new Metro(baseFare, stations);
		System.out.println("Metro transport added successfully.");
	}

	private static void addTaxi(Scanner scanner, Transport[] transports, int index) {

		System.out.print("Enter Base Fare: ");
		double baseFare = Helpers.validateDouble(scanner);

		System.out.print("Enter Distance (in km): ");
		double distance = Helpers.validateDouble(scanner);

		System.out.print("Enter Time (in minutes): ");
		double time = Helpers.validateDouble(scanner);

		if (baseFare < 0 || distance <= 0 || time <= 0) {
			System.out.println("Invalid values. All must be positive. Not added.");
			return;
		}

		transports[index] = new Taxi(baseFare, distance, time);
		System.out.println("Taxi transport added successfully.");
	}

	private static void printAllTickets(Transport[] transports, int count) {
		if (count == 0) {
			System.out.println("No transports have been added yet.");
			return;
		}

		System.out.println("\n--- All Transport Tickets ---");
		for (int i = 0; i < count; i++) {
			Transport t = transports[i];
			System.out.println("\nTransport #" + (i + 1) + ":");
			t.printTicket();
			System.out.println("-----------------------------");
		}
	}
}