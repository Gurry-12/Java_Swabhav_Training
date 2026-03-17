package com.gurpreet.comparator.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.gurpreet.comparator.comparators.MovieNameYearComparator;
import com.gurpreet.comparator.models.Movie;
import com.gurpreet.helpers.Helpers;

public class MovieTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Movie> movies = new ArrayList<>();

        System.out.println("=====================================");
        System.out.println("      Movie List ");
        System.out.println("=====================================");

        int n = getPositiveInteger(scanner, "How many movies do you want to add? ");

        for (int i = 1; i <= n; i++) {
            System.out.println("\nMovie #" + i + ":");
            String title = getNonEmptyString(scanner, "Enter movie title: ");
            int year = Helpers.getYear(scanner, "Enter release year (1900–2100): ");
            movies.add(new Movie(title, year));
        }

        System.out.println("\nBefore sorting:");
        printMovies(movies);

        Collections.sort(movies, new MovieNameYearComparator());

        System.out.println("\nAfter sorting (by Name → Year):");
        printMovies(movies);

        scanner.close();
    }

    private static int getPositiveInteger(Scanner scanner, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            value = scanner.nextInt();
            scanner.nextLine();
            if (value <= 0) System.out.println("Must be positive.");
        } while (value <= 0);
        return value;
    }

    

    private static String getNonEmptyString(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) System.out.println("Title cannot be empty.");
        } while (input.isEmpty());
        return input;
    }

    private static void printMovies(List<Movie> list) {
        System.out.println("Year   Movie Title");
        System.out.println("------------------------");
        for (Movie m : list) {
            System.out.printf("%d   %s%n", m.getYear(), m.getName());
        }
    }
}