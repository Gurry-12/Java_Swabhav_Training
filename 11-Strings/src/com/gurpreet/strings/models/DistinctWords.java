package com.gurpreet.strings.models;

public class DistinctWords {

	private String sentance;
	private String[] words;
	private String[] distinctWords;
	private int distinctCount;
	private String distinctString;

	public DistinctWords(String sentance) {
		this.sentance = sentance.toLowerCase();
	}

	public void distictWordFinder() {
		this.words = sentance.split("\\s+");

		this.distinctWords = new String[words.length];
		this.distinctCount = 0;

		for (String word : words) {
			boolean found = false;

			for (int j = 0; j < distinctCount; j++) {
				if (word.equals(distinctWords[j])) {
					found = true;
					break;
				}

			}

			if (!found) {
				distinctWords[distinctCount] = word;
				distinctCount++;
			}
		}

		this.distinctString = convertAndSkipNulls(distinctWords);
	}

	public void printDistinctString() {

		System.out.println("Distinct String : " + distinctString);
	}

	private String convertAndSkipNulls(String[] array) {
		if (array == null) {
			return ""; // Return an empty string if the whole array is null
		}

		StringBuilder resultBuilder = new StringBuilder();
		for (String s : array) {
			// Check for null before calling methods like length() or isEmpty()
			if (s != null && !s.isEmpty()) {
				resultBuilder.append(s);
				resultBuilder.append("\s");
			}
		}

		return resultBuilder.toString();
	}

}
