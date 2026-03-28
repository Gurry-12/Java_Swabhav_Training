package com.consumer.test;

import java.util.function.Consumer;

public class PrintStringInUppercase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<String> upperCase = str -> System.out
				.println("original str : " + str + " and the upper case is : " + str.trim().toUpperCase());

		upperCase.accept("gurpreet");

	}

}
