package com.gurpreet.strings.models;

public class SentanceFormatter {

	private String unFormattedString;
	private String formattedString;
	private String[] stringParts;
	private int stringLength;
	private String firstWord;
	private String lastWord;

	public SentanceFormatter(String unFormattedString) {
		this.unFormattedString = unFormattedString;
	}

	public void formateString() {

		// lowercase
		formattedString = unFormattedString.toLowerCase();

		// capitalized
		formattedString = formattedString.substring(0, 1).toUpperCase() + formattedString.substring(1);

		formattedString = formattedString.replace("fun", "interesting");

		stringParts = formattedString.split(" ");

		stringLength = stringParts.length;

		firstWord = stringParts[0];
		lastWord = stringParts[stringLength - 1];

	}

	public void output() {
		System.out.println("Formatted Sentence: " + formattedString);
		System.out.println("Total words: " + stringLength);
		System.out.println("First word: " + firstWord);
		System.out.println("Last word: " + lastWord);
	}

}
