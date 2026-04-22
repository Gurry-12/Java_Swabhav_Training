package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {

		// connection variables
		String url = "jdbc:mysql://localhost:3306/studentDB";
		String user = "root";
		String password = "Guriii123";

		try {

			// create connection
			Connection connection = DriverManager.getConnection(url, user, password);

			// Query
			String sqlQuery1 = "select * from student";

			// create prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery1);

			// execute query
			ResultSet resultSet = preparedStatement.executeQuery();

			// print table
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
