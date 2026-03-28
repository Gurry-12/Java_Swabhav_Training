package com.supplier.test;

import java.util.Scanner;
import java.util.function.Supplier;

public class DefaultCityProvider {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		Supplier<String> defaultCity = () -> "Pune";
		
		System.out.println("Enter your city name (or press Enter for default):");
		
		String city = scanner.nextLine();
		System.out.println("city is : " + (!city.trim().isEmpty() ? city : defaultCity.get()));
		
		
		scanner.close();

	}

}
