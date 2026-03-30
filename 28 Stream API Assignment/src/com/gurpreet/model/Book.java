package com.gurpreet.model;

import com.gurpreet.enums.Genre;
import com.gurpreet.exception.InvalidIdException;
import com.gurpreet.exception.InvalidPriceException;

public class Book {

	private String bookId;
	private String title;
	private String author;
	private Genre genre;
	private boolean available;
	private double price;

	// Parameterized Constructor
	public Book(String bookId, String title, String author, Genre genre, boolean available, double price)
			throws InvalidPriceException, InvalidIdException {

		if (bookId == null || bookId.trim().isEmpty()) {
			throw new InvalidIdException("Book ID cannot be empty.");
		}
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("Title cannot be empty.");
		}
		if (author == null || author.trim().isEmpty()) {
			throw new IllegalArgumentException("Author cannot be empty.");
		}
		if (genre == null ) {
			throw new IllegalArgumentException("Genre cannot be empty.");
		}
		if (price <= 0) {
			throw new InvalidPriceException("Price must be positive.");
		}

		this.bookId = bookId.toUpperCase();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.available = available;
		this.price = price;
	}

	// Getters
	public String getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre.getDisplayName();
	}

	public boolean isAvailable() {
		return available;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return String.format(
				"Book ID: %-10s | Title: %-25s | Author: %-18s | Genre: %-12s | Available: %-5s | Price: ₹%.2f", bookId,
				title, author, genre, available ? "Yes" : "No", price);
	}
}