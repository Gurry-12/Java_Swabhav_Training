package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class DisplayRecordGreaterThanMarks {

	public DisplayRecordGreaterThanMarks() {
	}

	public void recordGreaterThanSpecificMarks() {

		// Create Query
		String sqlQuery = "select * from student where marks > ? order by marks asc";

		try (Connection connection = ConnectionDatabase.getConnection(); 
				Scanner scanner = new Scanner(System.in)) {

			// prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			System.out.println("Enter marks (for select greater than it) : ");
			double marks1 = Helpers.validateDoublePositive(scanner);

			preparedStatement.setDouble(1, marks1);

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
