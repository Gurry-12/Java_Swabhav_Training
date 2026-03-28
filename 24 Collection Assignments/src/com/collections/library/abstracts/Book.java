package com.collections.library.abstracts;

import java.util.*;

import com.collections.library.enums.Status;
import com.collections.library.exceptions.InvalidBookException;

public abstract class Book {

	private String id;
	private String title;
	private String author;
	private Status status;

	public Book(String title, String author, Status status, String prefix , long counter) throws InvalidBookException {
		
		
		if (title == null || title.isEmpty()) {
			throw new InvalidBookException("Title can't be empty");
		}

		if (author == null || author.isEmpty()) {
			throw new InvalidBookException("Author can't be empty");
		}

		if (status == null) {
			throw new InvalidBookException("Status can't be null");
		}

		this.id = prefix + counter;
		this.title = title;
		this.author = author;
		this.status = status;
	}

	public String getStatus() {
		return status.toString();
	}
	
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(!(obj instanceof Book)) return false;
		
		Book other = (Book) obj;
		return other.title.equalsIgnoreCase(title) && other.author.equalsIgnoreCase(author);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), author.toLowerCase());
    }

	public abstract void printDetails();

	public void setStatus(Status issued) {
		status = issued;
		
	}
	
	
}
