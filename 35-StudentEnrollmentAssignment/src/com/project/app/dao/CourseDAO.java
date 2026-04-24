package com.project.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

	// select course names
	public List<String> getAllCourses(Connection connection) throws SQLException {

		String sqlQuery = "select course_name from course";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<String> allCourses = new ArrayList<>();
			
			while(resultSet.next()) {
				allCourses.add( 
						resultSet.getString("course_name"));
			}
			return allCourses;
		}
	}

}
