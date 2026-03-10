package com.gurpreet.streamio.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestFileManager {

	private static final String FILE_NAME = "Test.txt";
	
	
	public void createNewTestFile() {
		
		try {
		File file = new File(FILE_NAME);
		
		if(!file.createNewFile()) {
			throw new Exception("File exist allready");
		}
		
		System.out.println("File Created Successfully");
		
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public void writeFile(String data) throws IOException {
		
		try(FileWriter fileWriter = new FileWriter(FILE_NAME);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriter = new PrintWriter(bufferedWriter);) {
			
			printWriter.print(data);
			printWriter.println();
			System.out.println("Data Added ....");
		} 
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	
	
}
