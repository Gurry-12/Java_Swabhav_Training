package com.gurpreet.strings.models;

public class FileNameValidator {

	private String initalString;
	private String fileName;
	private String upperCaseFile;
	private String extensionName;
	private boolean isPDF = false;
	private boolean isFinalWord = false;

	public FileNameValidator(String fileName) {
		this.initalString = fileName;
	}

	public void validateFileName() {
		int indexOfDot = initalString.lastIndexOf(".");
		fileName = initalString.substring(0, indexOfDot);
		extensionName = initalString.substring(indexOfDot + 1);

		fileName = fileName.replace("_", " ");

		upperCaseFile = fileName.toUpperCase();

		isPDF = initalString.endsWith(".pdf");
		if (fileName.contains("final"))
			isFinalWord = true;

	}

	public void output() {
		System.out.println("File Name: " + fileName);
		System.out.println("Upper Case File Name: " + upperCaseFile );
		System.out.println("Extension: " + extensionName);
		System.out.println("Is PDF: " + (isPDF ? "Yes" : "No"));
		System.out.println("Contains 'final': " + (isFinalWord ? "Yes" : "No"));
	}
}
