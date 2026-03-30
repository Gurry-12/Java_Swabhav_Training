package com.gurpreet.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.model.Book;
import com.gurpreet.service.LibraryManagementSystem;

public class LibraryBookTest {

	public static void main(String[] args) {
		List<Book> books = new ArrayList<>();
		LibraryManagementSystem library = new LibraryManagementSystem();
		Scanner scanner = new Scanner(System.in);

		int choice;

		System.out.println("==================================================");
		System.out.println("     LIBRARY BOOK INVENTORY & ISSUE TRACKER");
		System.out.println("==================================================\n");
		System.out.println("System initialized with no books.\n");
		System.out.println("Please use Option 1 to add books.\n");

		try {
			do {
				displayMenu();
				System.out.print("Enter your choice (0-9): ");

				while (!scanner.hasNextInt()) {
					System.out.println("Invalid input! Please enter a valid number.");
					scanner.next();
				}

				choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					library.addNewBook(scanner, books);
					break;
				case 2:
					library.getAllAvailableBooks(books);
					break;
				case 3:
					library.groupBooksByGenre(books);
					break;
				case 4:
					library.countBooksGenreWise(books);
					break;
				case 5:
					library.findMostExpensiveBook(books);
					break;
				case 6:
					library.getTitlesSortedByPriceAscending(books);
					break;
				case 7:
					library.checkAnyUnavailableBook(books);
					break;
				case 8:
					library.getUniqueAuthors(books);
					break;
				case 9:
					library.displayAllBooks(books);
					break;
				case 0:
					System.out.println("\nThank you for using Library Book Inventory System. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice! Please enter a number between 0 and 9.");
				}

				if (choice != 0) {
					System.out.println("\n" + "=".repeat(60) + "\n");
				}

			} while (choice != 0);

		} catch (Exception e) {
			System.out.println("\nAn unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
		} finally {
			scanner.close();

		}
	}

	private static void displayMenu() {
		System.out.println("------------------- MAIN MENU -------------------");
		System.out.println("1.  Add New Book");
		System.out.println("2.  Get All Available Books");
		System.out.println("3.  Group Books by Genre");
		System.out.println("4.  Count Books Genre-wise");
		System.out.println("5.  Find Most Expensive Book");
		System.out.println("6.  Sorted Titles by Price (Ascending)");
		System.out.println("7.  Check Any Unavailable Book");
		System.out.println("8.  Get Unique Authors");
		System.out.println("9.  Display All Books");
		System.out.println("0.  Exit");
		System.out.println("-------------------------------------------------");
	}
}