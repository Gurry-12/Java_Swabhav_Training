package com.gurpreet.strings.test;

import java.util.Scanner;

import com.gurpreet.strings.models.DistinctWords;

public class DistinctWordsTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your string for eleminate duplicate. ...");
		
		String sentance = scanner.nextLine();
		
		DistinctWords words = new DistinctWords(sentance);
		words.distictWordFinder();
		words.printDistinctString();
		scanner.close();

	}

}
