package com.gurpreet.streamio.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentFileManager {
	private static final String FILE_NAME = "Students.txt";

	public void writeInFile(Student student) {

		try (FileWriter fileWriter = new FileWriter(FILE_NAME, true); 
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriter = new PrintWriter(bufferedWriter);) {
			String data = student.getData();
			printWriter.print(data);
			printWriter.println();
			System.out.println("Data Saved in file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void readInFile() {
		try (FileReader fileReader = new FileReader(FILE_NAME);
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            System.out.println("\n--- All Students in File ---");
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student student = Student.fromFile(line);
                    System.out.println(student.getData());
                }
            }
            System.out.println("--- End of file ---");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. No students yet.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
