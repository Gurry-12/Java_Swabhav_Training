package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class DeleteAllRecordWithMinimumMarks {

	public DeleteAllRecordWithMinimumMarks() {
	}

	public void deleteStudentRecordWithMarks() {
		// Create Query
		String sqlQuery = "delete from student where marks < ? ";

		try (Connection connection = ConnectionDatabase.getConnection(); 
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Enter marks, (whose less you want to delete) : ");
			double marks = Helpers.validateDoublePositive(scanner);

			// prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// insert values in placeholder
			preparedStatement.setDouble(1, marks);

			// execute query
			int status = preparedStatement.executeUpdate();

			if (status != 0) {
				System.out.println("Data deleted successfully.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
