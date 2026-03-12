package com.gurpreet.strings.models;

public class WordFrequencyCount {

	private String sentance;
	private String[] words;
	private String[] distinctWords;
	private int[] count; 
	private int distinctCount;

	public WordFrequencyCount(String sentance) {
		this.sentance = sentance;
	}

	public void validateSentance() {
		sentance = sentance.toLowerCase();

		words = sentance.split("\\s+");
		countWords();
	}

	private void countWords() {
		// Worst case: every word is unique
		this.distinctWords = new String[words.length];
		this.count = new int[words.length];
		this.distinctCount = 0;

		// Step 2: fill distinctWords and count
		for (String word : words) {
			// Try to find if word already exists
			boolean found = false;

			for (int j = 0; j < distinctCount; j++) {
				if (word.equals(distinctWords[j])) {
					count[j]++;
					found = true;
					break;
				}
			}

			// Not found → add as new word
			if (!found) {
				distinctWords[distinctCount] = word;
				count[distinctCount] = 1;
				distinctCount++;
			}

		}
	}

	public void printFrequencies() {
		System.out.println("Word\t\tCount");
		System.out.println("-------------------");
		for (int i = 0; i < distinctCount; i++) {
			System.out.printf("%-15s %d%n", distinctWords[i], count[i]);
		}
	}

}
