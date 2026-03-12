package com.gurpreet.strings.models;

public class EmailAnalyzer {

	private String email;
	private String userName;
	private String domainName;
	private int userNameLength;
	private String modifiedUserName;
	private boolean isGmail;
	private boolean hasDigits;

	public EmailAnalyzer(String email) {
		this.email = email.toLowerCase();
	}

	public void extractUserNameAndDomain() {

		int indexAt = email.indexOf("@");
		userName = email.substring(0, indexAt); // task 3
		domainName = email.substring(indexAt + 1); // task 4
		userNameLength = userName.length(); // task 5
		modifiedUserName = userName.replace(".", "_"); // task 6
		isGmail = domainName.equalsIgnoreCase("gmail.com");
		hasDigits = userName.matches(".*\\d.*");
	}

	public void output() {
		System.out.println("Username: " + userName);
		System.out.println("Domain: " +  (isGmail ? domainName : "Not Gmail - " + domainName));
		System.out.println("Total characters in username: " + userNameLength);
		System.out.println("Contains digits: " + (hasDigits ? "Yes" : "No"));
		System.out.println("Modified username: " + modifiedUserName);

	}

}
