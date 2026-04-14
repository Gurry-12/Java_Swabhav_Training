package com.collections.library.test;

import java.util.Scanner;

import com.collections.library.models.BookUtility;
import com.collections.library.models.InvalidBookException;
import com.collections.library.models.Library;
import com.collections.student.models.Helpers;

public class BookManagementTest {

	public static void main(String[] args) throws InvalidBookException {
		try (Scanner scanner = new Scanner(System.in)) {

			Library library = new Library(scanner);
			String bookId;
			System.out.println("------- ------------- -----------");
			System.out.println(" Library management system ");
			System.out.println("------- ------------- -----------\n");

			boolean isRun = true;
			while (isRun) {
				try {
					BookUtility.DisplayMenu();

					int choice = Helpers.validateIntRange(scanner, 1, 9);
					switch (choice) {

					case 1:
						library.addBook();
						break;

					case 2:
						bookId = Helpers.validateBookId(scanner);
						library.printBook(bookId);
						break;
					case 3:
						library.printAllBooks();
						break;

					case 4:
						bookId = Helpers.validateBookId(scanner);
						library.removeBook(bookId);
						break;

					case 5:
						bookId = Helpers.validateBookId(scanner);
						library.issueBook(bookId);
						break;

					case 6:
						library.returnBook();
						break;

					case 7:
						System.out.println("Enter the Author name ");
						String author = Helpers.validateStringLettersOnly(scanner);
						library.findBook(author);
						break;

					case 8:
						System.out.println("Enter the Book Title");
						String title = Helpers.validateStringLettersOnly(scanner);
						library.findBook(title);
						break;
					case 9:
						isRun = false;
						System.out.println("Thanks for visit");
						break;

					default:
						System.out.println("Enter valid Input");
					}

				} catch (InvalidBookException e) {
					System.out.println(e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
