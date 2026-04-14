package com.collections.library.models;

import java.util.Scanner;


public class BookUtility {

	public static void displayBookTypes() {
		System.out.println("Here are the book types: ");
		System.out.println("1. Academic Book");
		System.out.println("2. Magazine ");

	}

	public static int PrintStatus() {
		Status[] status = Status.values();

		for (int i = 0; i < status.length; i++) {
			System.out.println(i + 1 + " : " + status[i]);
		}
		return status.length;
	}

	public static Category PrintAndGetCategory(Scanner scanner) {

		Category[] category = Category.values();

		for (int i = 0; i < category.length; i++) {
			System.out.println(i + 1 + " : " + category[i]);
		}
		
		int categoryVal = Helpers.validateIntRange(scanner, 1, category.length);
		return category[categoryVal - 1];
	}

	public static PublishType PrintAndGetPublishType(Scanner scanner) {
		PublishType[] publishType = PublishType.values();

		for (int i = 0; i < publishType.length; i++) {
			System.out.println(i + 1 + " : " + publishType[i]);
		}
		
		int publishTypeVal = Helpers.validateIntRange(scanner, 1, publishType .length);
		return publishType[publishTypeVal - 1];
	}


	public static void DisplayMenu() {
		System.out.println("Library Management System Menu:");
	    System.out.println("1. Add book");
	    System.out.println("2. Find book by ID");
	    System.out.println("3. View all books");
	    System.out.println("4. Remove book");
	    System.out.println("5. Issue book");
	    System.out.println("6. Return book (old)");
	    System.out.println("7. Search books by author");
	    System.out.println("8. Search books by title");
	    System.out.println("9. Exit");
		
	}
}
