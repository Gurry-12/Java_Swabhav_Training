package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class DisplayAllRecord {

	public DisplayAllRecord() {
	}

	/*
	 * 3. Display all records from the student table.
	 */
	public void getAllRecords() {

		// Create Query
		String sqlQuery = "select * from student";

		try (Connection connection = ConnectionDatabase.getConnection()) {

			// prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// execute query
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// print 
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String branch = resultSet.getString("branch");
				double marks = resultSet.getDouble("marks");

				System.out.println(id + " " + name + " " + age + " " + branch + " " + marks);
			}



		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
