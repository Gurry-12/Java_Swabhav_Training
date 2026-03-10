package com.gurpreet.strings.models;

public class PasswordPolicy {
	
	private String password;
	private boolean hasDigit = false;
	private boolean hasUpperCase = false;
	private boolean hasLowerCase = false;
	private final int minLength = 8;
	private boolean validLength = false;
	private int passwordLength;
	private boolean isValidPassword = false;
	
	public PasswordPolicy(String password) {
		this.password = password;
	}

	public void validatePassword() {
		
		password = password.replace(" ", "");
		// check length 
		passwordLength = password.length();
		
		// validate Length
		validLength = passwordLength >= minLength;
		
		// checks for uppercase , digit, lowercase
		for(int val = 0 ; val < passwordLength; val++) {
			char ch = password.charAt(val);
			
			if(Character.isUpperCase(ch)) hasUpperCase = true;
			if(Character.isLowerCase(ch)) hasLowerCase = true;
			if(Character.isDigit(ch)) hasDigit = true;
		}
		
		if(validLength && hasUpperCase && hasLowerCase && hasDigit) isValidPassword = true;
		

		
	}

	public void output() {
		
		System.out.println("Password: " + password);
        System.out.println("Length valid: " + (validLength ? "Yes" : "No"));
        System.out.println("Contains uppercase: " + (hasUpperCase ? "Yes" : "No"));
        System.out.println("Contains lowercase: " + (hasLowerCase ? "Yes" : "No"));
        System.out.println("Contains digit: " + (hasDigit ? "Yes" : "No"));
       
        System.out.println("Password is " + (isValidPassword ? "VALID" : "INVALID"));
        
        if(!isValidPassword) {
			System.out.print("Reason: ");
            if (!validLength) System.out.print("Length < 8; ");
            if (!hasUpperCase) System.out.print("No uppercase; ");
            if (!hasLowerCase) System.out.print("No lowercase; ");
            if (!hasDigit) System.out.print("No digit");
            System.out.println();
            return;
		}
		
	}

}
