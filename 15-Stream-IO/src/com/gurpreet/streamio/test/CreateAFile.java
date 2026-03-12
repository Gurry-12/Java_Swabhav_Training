package com.gurpreet.streamio.test;

import java.io.File;
import java.io.IOException;

public class CreateAFile {

	private static final String FILE_NAME = "Test.txt";

	public static void main(String[] args) {
		
		try {
		File file = new File(FILE_NAME);
		
		if(file.createNewFile()) {
			
			System.out.println(file.canRead());
			System.out.println(file.canExecute());
			System.out.println(file.canWrite());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getName());
		}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
