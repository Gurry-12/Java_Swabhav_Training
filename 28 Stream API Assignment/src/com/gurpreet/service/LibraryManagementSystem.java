package com.gurpreet.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.gurpreet.enums.Department;
import com.gurpreet.enums.Genre;
import com.gurpreet.exception.InvalidIdException;
import com.gurpreet.exception.InvalidPriceException;
import com.gurpreet.model.Book;
import com.gurpreet.utility.Helpers;

public class LibraryManagementSystem {

	public void getAllAvailableBooks(List<Book> books) {
		System.out.println("\n--- All Available Books ---");
		List<Book> availableBooks = books.stream().filter(Book::isAvailable).collect(Collectors.toList());

		if (availableBooks.isEmpty()) {
			System.out.println("No available books found.");
			return;
		}
		availableBooks.forEach(System.out::println);
	}

	public void groupBooksByGenre(List<Book> books) {
		System.out.println("\n--- Books Grouped by Genre ---");
		Map<String, List<Book>> grouped = books.stream().collect(Collectors.groupingBy(Book::getGenre));

		grouped.forEach((genre, list) -> {
			System.out.println("\nGenre: " + genre);
			list.forEach(System.out::println);
		});
	}

	public void countBooksGenreWise(List<Book> books) {
		System.out.println("\n--- Genre-wise Book Count ---");
		Map<String, Long> countMap = books.stream()
				.collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));

		countMap.forEach((genre, count) -> System.out.println(genre + " : " + count + " book(s)"));
	}

	public void findMostExpensiveBook(List<Book> books) {
		System.out.println("\n--- Most Expensive Book ---");
		Optional<Book> expensiveBook = books.stream().max(Comparator.comparingDouble(Book::getPrice));

		if (expensiveBook.isPresent()) {
			System.out.println(expensiveBook.get());
		} else {
			System.out.println("No books found.");
		}
	}

	public void getTitlesSortedByPriceAscending(List<Book> books) {
		System.out.println("\n--- Book Titles Sorted by Price (Ascending) ---");
		List<String> sortedTitles = books.stream().sorted(Comparator.comparingDouble(Book::getPrice))
				.map(Book::getTitle).collect(Collectors.toList());

		for (int i = 0; i < sortedTitles.size(); i++) {
			System.out.println((i + 1) + ". " + sortedTitles.get(i));
		}
	}

	public void checkAnyUnavailableBook(List<Book> books) {
		System.out.println("\n--- Unavailable Books Check ---");
		boolean hasUnavailable = books.stream().anyMatch(b -> !b.isAvailable());

		if (hasUnavailable) {
			System.out.println("Yes, there is at least one unavailable book.");
		} else {
			System.out.println("All books are currently available.");
		}
	}

	public void getUniqueAuthors(List<Book> books) {
		System.out.println("\n--- Unique Authors ---");
		Set<String> authors = books.stream().map(Book::getAuthor).collect(Collectors.toSet());

		if (authors.isEmpty()) {
			System.out.println("No authors found.");
			return;
		}

		authors.forEach(author -> System.out.println("- " + author));
		System.out.println("Total Unique Authors: " + authors.size());
	}

	public void displayAllBooks(List<Book> books) {
		System.out.println("\n--- All Books in Inventory ---");
		if (books.isEmpty()) {
			System.out.println("No books in the library.");
		} else {
			books.forEach(System.out::println);
		}
	}

	// Add New Book
	public void addNewBook(Scanner scanner, List<Book> books) {
		System.out.println("\n=== Add New Book ===");

		try {
			String bookId = validateBookId(scanner, books); // Using existing helper
			String title = getValidTitle(scanner);
			String author = getValidAuthor(scanner);
			Genre genre = getValidGenre(scanner);
			boolean available = getValidAvailability(scanner);
			double price = getValidPrice(scanner);

			Book newBook = new Book(bookId, title, author, genre, available, price);
			books.add(newBook);

			System.out.println("\nBook added to inventory successfully!");
			System.out.println(newBook);

		} catch (InvalidIdException |InvalidPriceException e) {
			System.out.println("Error: " + ((Throwable) e).getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
	}

	private String validateBookId(Scanner scanner, List<Book> books) {
			while (true) {
				System.out.print("Enter Book ID (e.g. BK123): ");
				String id = scanner.nextLine().trim().toUpperCase();
				if (id.isEmpty()) {
					System.out.println("Book ID cannot be empty.");
					continue;
				}
				if (books.stream().anyMatch(book -> book.getBookId().equals(id))) {
					System.out.println("Book ID already exists.");
					continue;
				}
				return id;
			}
		}
	}

	// Helper Methods
	private String getValidTitle(Scanner scanner) {
		System.out.print("Enter Book Title: ");
		return Helpers.validateStringAlphanumeric(scanner);
	}

	private String getValidAuthor(Scanner scanner) {
		System.out.print("Enter Author Name: ");
		return Helpers.validateStringLettersOnly(scanner);
	}

	private Genre getValidGenre(Scanner scanner) {
		Genre[] genres = Genre.values();

		System.out.print("Select Genre: ");
		for (int i = 0; i < genres.length; i++) {
			System.out.println((i + 1) + " : " + genres[i]);
		}
		int choice = Helpers.validateIntRange(scanner, 1, genres.length);
		return genres[choice - 1];

	}

	private boolean getValidAvailability(Scanner scanner) {
		while (true) {
			System.out.print("Is book available? (yes/no): ");
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.equals("yes") || input.equals("y"))
				return true;
			if (input.equals("no") || input.equals("n"))
				return false;
			System.out.println("Please enter yes or no.");
		}
	}

	private double getValidPrice(Scanner scanner) {
		System.out.print("Enter Price (₹): ");
		return Helpers.validateDoublePositive(scanner);
	}
}