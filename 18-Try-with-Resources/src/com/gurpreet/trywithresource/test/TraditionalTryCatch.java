package com.gurpreet.trywithresource.test;

import java.io.File;
import java.util.Scanner;

public class TraditionalTryCatch {

	public static void main(String[] args) {
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("text.txt"));
			
			String newString = scanner.nextLine();
			System.out.println(newString);
			
			scanner.close();
		}
		catch( Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}

	}

}
