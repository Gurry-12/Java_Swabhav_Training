package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class UpdateRecordMarksByBranch {

	public UpdateRecordMarksByBranch() {}
	
	public void updateMarksByBranch() {
		// Create Query
		String sqlQuery = "UPDATE student SET marks = marks + ? WHERE branch = ? AND (marks + ?) <= 100;";

		
				try (Connection connection = ConnectionDatabase.getConnection(); 
						Scanner scanner = new Scanner(System.in)) {

					System.out.println("Enter branch, (whose marks will increment) : ");
					String branch = Helpers.validateStringLettersOnly(scanner);
					System.out.println("Enter marks to increment in database: ");
					Double marks = Helpers.validateDoubleRange(scanner, 0, 100);

					// prepared Statement
					PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

					// insert values in placeholder
					preparedStatement.setDouble(1, marks);
					preparedStatement.setString(2, branch);
					preparedStatement.setDouble(3, marks); 

					// execute query
					int status = preparedStatement.executeUpdate();

					if (status != 0) {
						System.out.println("Data updated successfully.");
					}

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
	}
}
