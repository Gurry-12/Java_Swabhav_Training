package com.gurpreet.strings.test;

import java.util.Scanner;
import com.gurpreet.strings.models.SentanceFormatter;

public class SentenceFormatterTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Sentence Formatter");
		
		System.out.println("Enter your string....");
		String unFormattedString = scanner.nextLine().trim();
		
		SentanceFormatter formatter = new SentanceFormatter(unFormattedString);
		
		formatter.formateString();
		formatter.output();
		
		scanner.close();
	}
}
