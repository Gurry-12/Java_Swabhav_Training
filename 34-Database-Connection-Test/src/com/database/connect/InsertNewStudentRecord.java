package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class InsertNewStudentRecord {

	public InsertNewStudentRecord() {
	}

	/*
	 * 1. Insert a new student record into the student table.
	 */
	
	public void insertNewStudentInDatabase() {

		// Create Query
		String sqlQuery = "insert into student (name, age, branch, marks) values (? , ?, ? , ? );";

		try (Connection connection = ConnectionDatabase.getConnection(); 
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Enter data to insert in database");
			System.out.println("Enter name : ");
			String name = Helpers.validateStringLettersOnly(scanner);
			System.out.println("Enter age : ");
			int age = Helpers.validateIntPositive(scanner);
			System.out.println("Enter branch : ");
			String branch = Helpers.validateStringLettersOnly(scanner);
			System.out.println("Enter marks : ");
			double marks = Helpers.validateDoublePositive(scanner);

			// prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// insert values in placeholder
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, branch);
			preparedStatement.setDouble(4, marks);

			// execute query
			int status = preparedStatement.executeUpdate();

			if (status != 0) {
				System.out.println("Data inserted successfully.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
