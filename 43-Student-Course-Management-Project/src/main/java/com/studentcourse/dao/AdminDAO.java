package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

	public boolean verifyAdmin(Connection connection, String username, String password) throws SQLException {
		String sqlQuery = "select * from admin where username = ? and password = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

}
