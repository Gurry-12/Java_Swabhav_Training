package com.gurpreet.strings.models;

public class ProductCodeParser {

	private String[] parts;
	private String category;
	private String product;
	private String year;
	private boolean isTV;
	private boolean isYear2023;
	private String modifiedCode;
	private String code;
	private int firstHyphenIndex;

	public ProductCodeParser(String code, String[] parts) {
		this.code = code;
		this.parts = parts;
	}

	public void extractValues() {
		category = parts[0].toUpperCase();
		product = parts[1].toUpperCase();
		year = parts[2];
		isTV = product.startsWith("TV");
		isYear2023 = year.endsWith("2023");
		modifiedCode = category + " " + product + " " + year;
		firstHyphenIndex = code.indexOf("-");

	}

	public void output() {
		System.out.println("Category: " + category);
		System.out.println("Product: " + product);
		System.out.println("Year: " + year);
		System.out.println("Starts with TV: " + (isTV ? "Yes" : "No"));
		System.out.println("Ends with 2023: " + (isYear2023 ? "Yes" : "No"));
		System.out.println("Modified Code: " + modifiedCode);
		System.out.println("First hyphen position: " + firstHyphenIndex);
	}

}
