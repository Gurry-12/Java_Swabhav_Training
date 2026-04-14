package com.collections.library.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import com.collections.student.models.Helpers;

public class Library {

	private Set<Book> books;
	private Queue<Book> issuedBooks;
	private Scanner scanner;
	private Book book;

	public Library(Scanner scanner) {
		this.books = new HashSet<Book>();
		this.issuedBooks = new LinkedList<Book>();
		this.scanner = scanner;

	}

	public void addBook() throws InvalidBookException {

		BookUtility.displayBookTypes();
		int choice = Helpers.validateIntRange(scanner, 1, 2);

		switch (choice) {

		case 1:
			book = createAcadamicBook(scanner);
			break;

		case 2:
			book = createMagazine(scanner);
			break;

		default:
			System.out.println("Please enter valid inputs.");
		}

		for(Book exist : books) {
			if(exist.equals(book)) {
				throw new InvalidBookException("Cannot add book. - with same name and author");
			}
		}
		
		books.add(book);
		System.out.println("Book Added ... ");
	}

	private Book createMagazine(Scanner scanner) throws InvalidBookException {
		System.out.println("Enter Book Title ");
		String title = Helpers.validateStringNonEmpty(scanner);

		System.out.println("Enter the Author Name");
		String author = Helpers.validateStringNonEmpty(scanner);

		Status status = Status.AVAILABLE;

		PublishType publishType = BookUtility.PrintAndGetPublishType(scanner);

		book = new Magazine(title, author, status, publishType);
		return book;
	}

	private Book createAcadamicBook(Scanner scanner) throws InvalidBookException {

		System.out.println("Enter Book Title ");
		String title = Helpers.validateStringNonEmpty(scanner);

		System.out.println("Enter the Author Name");
		String author = Helpers.validateStringNonEmpty(scanner);

		Status status = Status.AVAILABLE;

		System.out.println("Enter Publish Year");
		int year = Helpers.validateIntRange(scanner, 1600, 2026);

		System.out.println("Enter Book Category");
		Category category = BookUtility.PrintAndGetCategory(scanner);

		book = new AcademicBook(title, author, status, year, category);

		return book;

	}

	public void printBook(String bookId) throws InvalidBookException {

		Iterator<Book> iterator = books.iterator();
		Book bookToFind = null;
		while (iterator.hasNext()) {
			Book book = iterator.next();

			if (book.getId().equals(bookId)) {
				bookToFind = book;
			}
		}
		if (bookToFind == null) {
			throw new InvalidBookException("Book not Found");
		}

		bookToFind.printDetails();

	}

	public void printAllBooks() throws InvalidBookException {
		if (books.isEmpty()) {
			throw new InvalidBookException("Books not Found");
		}

		ArrayList<Book> allBooks = new ArrayList<Book>(books);
		Collections.sort(allBooks, new TitleIdComparator());
		int counter = 0;
		for (Book book : allBooks) {

			System.out.println("Book : " + ++counter);
			book.printDetails();
			System.out.println("\n ============================");
		}
	}

	public void issueBook(String bookId) {
		Book bookToIssue = null;
		for (Book b : books) {
			if (b.getId().equals(bookId)) {
				bookToIssue = b;
				break;
			}
		}

		if (bookToIssue == null) {
			System.out.println("Book not found.");
			return;
		}

		if (bookToIssue.getStatus() != Status.AVAILABLE.toString()) {
			System.out.println("Book is not available for issue.");
			return;
		}

		bookToIssue.setStatus(Status.ISSUED);
		issuedBooks.add(bookToIssue);

		System.out.println("Book id : " + bookId + " issued SuccessFully");

	}

	public void removeBook(String bookId) throws InvalidBookException {
		Iterator<Book> iterator = books.iterator();
		Book bookToRemove = null;
		while (iterator.hasNext()) {
			Book book = iterator.next();

			if (book.getId().equals(bookId)) {
				bookToRemove = book;
				break;
			}
		}

		if (bookToRemove == null) {
			throw new InvalidBookException("Book not Found");
		}

		if (bookToRemove.getStatus() == Status.ISSUED.toString()) {
			throw new InvalidBookException("This book can't be Remove : ISSUED");
		}

		books.remove(bookToRemove);
		System.out.println("Book removed successfully.");
	}

	public void findBook(String searchTerm) throws InvalidBookException {

		if (searchTerm == null || searchTerm.trim().isEmpty()) {
			throw new InvalidBookException("Search term cannot be empty.");
		}

		String term = searchTerm.trim().toLowerCase();
		int count = 0;

		System.out.println("Search results for \"" + searchTerm + "\":");

		for (Book book : books) {
			if (book.getTitle().toLowerCase().contains(term) || book.getAuthor().toLowerCase().contains(term)) {

				System.out.println("\nMatch " + (++count) + ":");
				book.printDetails();
				System.out.println("-----------------------------");
			}
		}

		if (count == 0) {
			throw new InvalidBookException("No books found matching the search term.");
		}
	}

	public void returnBook() throws InvalidBookException {
		if (issuedBooks.isEmpty()) {
			System.out.println("No books are currently issued.");
			return;
		}
		Book oldestIssuedBook = issuedBooks.poll();

		if (oldestIssuedBook == null) {
			throw new InvalidBookException("Queue is null");
		}

		if (!books.contains(oldestIssuedBook)) {
			throw new InvalidBookException("Issued book not found in library.");
		}

		oldestIssuedBook.setStatus(Status.AVAILABLE);

		System.out.println("Book returned successfully (oldest issued book):");
		System.out.println("  Title:  " + oldestIssuedBook.getTitle());
		System.out.println("  Author: " + oldestIssuedBook.getAuthor());
		System.out.println("  ID:     " + oldestIssuedBook.getId());

	}

}
