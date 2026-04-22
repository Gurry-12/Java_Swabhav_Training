package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class UpdateRecordBranchAndMarksWithId {

	public UpdateRecordBranchAndMarksWithId() {}

	public void updateStudnetBranchAndMarksWithId() {
		// Create Query
		String sqlQuery = "update student set branch = ? , marks = ? where id = ? ";

		try (Connection connection = ConnectionDatabase.getConnection(); 
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Enter Id, (whose branch and marks will update) : ");
			int id = Helpers.validateIntPositive(scanner);
			System.out.println("Enter branch to update in database: ");
			String branch = Helpers.validateStringLettersOnly(scanner);
			System.out.println("Enter marks to update in database: ");
			Double marks = Helpers.validateDoublePositive(scanner);
			

			// prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// insert values in placeholder
			preparedStatement.setString(1, branch);
			preparedStatement.setDouble(2, marks);
			preparedStatement.setInt(3, id);

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
