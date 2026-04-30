package com.project.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

	// select course names
	public List<String> getAllDepartments(Connection connection) throws SQLException {

		String sqlQuery = "select department_name from department";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			List<String> allDepartment = new ArrayList<>();

			while (resultSet.next()) {
				allDepartment.add(resultSet.getString("department_name"));
			}
			return allDepartment;
		}
	}

	public int addNewDepartment(Connection connection, String department) throws SQLException {

		String sqlQuery = "insert into department (department_name) values(?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setString(1, department);
			
			return preparedStatement.executeUpdate();
		}
	}
}
