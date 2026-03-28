package com.collections.library.models;

import com.collections.library.abstracts.Book;
import com.collections.library.enums.Category;
import com.collections.library.enums.Status;
import com.collections.library.exceptions.InvalidBookException;

public class AcademicBook extends Book {

	private static long counter = 25000;
	private static final String prefix = "AC";
	private int publishYear;
	private Category category;

	public AcademicBook(String title, String author, Status status, int publishYear, Category category)
			throws InvalidBookException {
		super(title, author, status, prefix, counter++);

		if (publishYear < 0) {
			throw new InvalidBookException("Publish Year Can't be negative");
		}

		if (category == null) {
			throw new InvalidBookException("Category can't be null");
		}

		this.publishYear = publishYear;
		this.category = category;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public String getCategory() {
		return category.toString();
	}

	@Override
	public void printDetails() {
		System.out.println(" Book Id : " + getId());
		System.out.println(" Book Title : " + getTitle());
		System.out.println(" Book Author : " + getAuthor());
		System.out.println(" Book Status : " + getStatus());
		System.out.println(" Publish Year : " + publishYear);
		System.out.println(" Category : " + getCategory());
	}

}
