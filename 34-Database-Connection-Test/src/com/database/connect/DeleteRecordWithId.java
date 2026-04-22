package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class DeleteRecordWithId {

	public DeleteRecordWithId() {}
	
	public void deleteStudentRecordWithId() {
		// Create Query
		String sqlQuery = "delete from student where id = ? ";

		try (Connection connection = ConnectionDatabase.getConnection(); 
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Enter Id, (whom you want to delete) : ");
			int id = Helpers.validateIntPositive(scanner);
			

			// prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			// insert values in placeholder
			preparedStatement.setInt(1, id);

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
