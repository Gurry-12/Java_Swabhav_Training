package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class DisplayRecordAgeBetweenRange {

	public DisplayRecordAgeBetweenRange() {}

	public void recordAgeInBetweenRange() {
		
		// Create Query
				String sqlQuery = "select * from student where age between ? and ? order by age asc";

				try (Connection connection = ConnectionDatabase.getConnection(); 
						Scanner scanner = new Scanner(System.in)) {

					// prepared Statement
					PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

					System.out.println("Enter lowest range of age: ");
					int age1 = Helpers.validateIntPositive(scanner);
					
					System.out.println("Enter highest range of age: ");
					int age2 = Helpers.validateIntPositive(scanner);

					if(age1 > age2) {
						preparedStatement.setInt(1, age2);
						preparedStatement.setInt(2, age1);
					}
					else {
					preparedStatement.setInt(1, age1);
					preparedStatement.setInt(2, age2);
					}

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
