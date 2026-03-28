package com.collections.library.comparator;

import java.util.Comparator;

import com.collections.library.abstracts.Book;

public class TitleIdComparator implements Comparator<Book> {

	@Override
	public int compare(Book book1, Book book2) {
		
		int titleResult = book1.getTitle().compareTo(book2.getTitle());
		
		if(titleResult != 0) {
			return titleResult;
		}
		
		return book1.getId().compareTo(book2.getId());
	}

}
