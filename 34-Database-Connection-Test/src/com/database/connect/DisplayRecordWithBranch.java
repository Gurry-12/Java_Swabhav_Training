package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class DisplayRecordWithBranch {

	public DisplayRecordWithBranch() {
	}

	/*
	 * 5. Display all students belonging to a specific branch.
	 */
	public void recordWithBranch() {

		// Create Query
		String sqlQuery = "select * from student where branch = ?";

		try (Connection connection = ConnectionDatabase.getConnection(); 
				Scanner scanner = new Scanner(System.in)) {

			// prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			System.out.println("Enter branch : ");
			String branch1 = Helpers.validateStringLettersOnly(scanner);

			preparedStatement.setString(1, branch1);

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
