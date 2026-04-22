package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class InsertRecordWithBatchProcessing {
	
	public InsertRecordWithBatchProcessing() {}
	
	/*
	 * 2. Insert 5 student records using batch processing.
	 */
	
	public void insertRecordWithBatch() {
		// Create Query
				String sqlQuery = "insert into student (name, age, branch, marks) values (? , ?, ? , ? );";

				try (Connection connection = ConnectionDatabase.getConnection(); 
						Scanner scanner = new Scanner(System.in)) {
					
					// prepared Statement
					PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
					
					//Set auto-commit to false
					connection.setAutoCommit(false);
					
					for(int i = 0 ; i < 2 ; i ++) {
						
						addDataInQueries(preparedStatement, scanner);
						preparedStatement.addBatch();
					}

					// execute query
					int[] status = preparedStatement.executeBatch();

					if (status.length != 0) {
						System.out.println("Data inserted successfully.");
					}
					
					//Explicitly commit statements to apply changes
					connection.commit();

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		
	}

	private void addDataInQueries(PreparedStatement preparedStatement, Scanner scanner) throws SQLException {
		System.out.println("Enter data to insert in database");
		System.out.println("Enter name : ");
		String name = Helpers.validateStringLettersOnly(scanner);
		System.out.println("Enter age : ");
		int age = Helpers.validateIntPositive(scanner);
		System.out.println("Enter branch : ");
		String branch = Helpers.validateStringLettersOnly(scanner);
		System.out.println("Enter marks : ");
		double marks = Helpers.validateDoublePositive(scanner);
		
		// insert values in placeholder
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, age);
		preparedStatement.setString(3, branch);
		preparedStatement.setDouble(4, marks);
		
	}

}
