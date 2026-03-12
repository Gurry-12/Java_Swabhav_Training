package com.gurpreet.strings.test;

import java.util.Scanner;

import com.gurpreet.strings.models.FileNameValidator;

public class FileNameValidatorTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter File Name to validate");
		String fileName = scanner.nextLine();
		
		FileNameValidator file = new FileNameValidator(fileName);
		
		file.validateFileName();
		file.output();
		
		
		scanner.close();
		
	}
}
