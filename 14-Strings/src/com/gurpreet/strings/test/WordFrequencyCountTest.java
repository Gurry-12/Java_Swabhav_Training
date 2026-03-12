package com.gurpreet.strings.test;

import java.util.Scanner;

import com.gurpreet.strings.models.WordFrequencyCount;

public class WordFrequencyCountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the String.");
		
		String sentance = scanner.nextLine();
		
		WordFrequencyCount frequency = new WordFrequencyCount(sentance);
		
		frequency.validateSentance();
		frequency.printFrequencies();
		scanner.close();
	}
}
