package com.collections.library.models;

import com.collections.library.abstracts.Book;
import com.collections.library.enums.PublishType;
import com.collections.library.enums.Status;
import com.collections.library.exceptions.InvalidBookException;

public class Magazine extends Book {

	private PublishType publishType;
	private static long counter = 15000;
	private static final String prefix = "MZ";

	public Magazine(String title, String author, Status status, PublishType publishType) throws InvalidBookException {
		super(title, author, status, prefix , counter++);

		if (publishType == null) {
			throw new InvalidBookException("Pubish type can't null");
		}

		this.publishType = publishType;
	}

	public String getPublishType() {
		return publishType.toString();
	}

	@Override
	public void printDetails() {
		System.out.println(" Book Id : " + getId());
		System.out.println(" Book Title : " + getTitle());
		System.out.println(" Book Author : " + getAuthor());
		System.out.println(" Book Status : " + getStatus());
		System.out.println(" Publish Type : " + getPublishType());
	}
}
