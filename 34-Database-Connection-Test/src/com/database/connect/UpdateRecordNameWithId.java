package com.database.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.helper.Helpers;

public class UpdateRecordNameWithId {

	public UpdateRecordNameWithId() {}

	public void updateStudnetNameWithId() {
		// Create Query
				String sqlQuery = "update student set name = ? where id = ? ";

				try (Connection connection = ConnectionDatabase.getConnection(); 
						Scanner scanner = new Scanner(System.in)) {

					System.out.println("Enter Id, (whose name will update) : ");
					int id = Helpers.validateIntPositive(scanner);
					System.out.println("Enter name to update in database: ");
					String name = Helpers.validateStringLettersOnly(scanner);
					

					// prepared Statement
					PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

					// insert values in placeholder
					preparedStatement.setString(1, name);
					preparedStatement.setInt(2, id);

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
