package com.gurpreet.strings.test;

import java.util.Scanner;

import com.gurpreet.strings.models.ProductCodeParser;

public class ProductCodeParserTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("--------------------------------\n" + "	Product Code Parser \n" + "--------------------------------");

		System.out.println("\n Enter the code");
		
		String code = scanner.nextLine();
		
		String[] parts = code.split("-");
		
		if(parts.length != 3) {
			System.out.println("Invalid Format of code ");
			scanner.close();
			return;
		}
		
		ProductCodeParser parser = new ProductCodeParser(code, parts);
		parser.extractValues();
		parser.output();
		
		scanner.close();
		
		
	}

}
