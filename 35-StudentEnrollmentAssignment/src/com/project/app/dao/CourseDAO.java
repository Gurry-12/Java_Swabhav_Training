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

			while (resultSet.next()) {
				allCourses.add(resultSet.getString("course_name"));
			}
			return allCourses;
		}
	}

	public int addNewCourses(Connection connection, String course) throws SQLException {

		String sqlQuery = "insert into course (course_name) values(?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setString(1, course);
			return preparedStatement.executeUpdate();
		}
	}

	public List<String> getStudentAllCourses(Connection connection, int studentId) throws SQLException {
		String sqlQuery = "select c.course_name from course c "
				+ "join registration r on c.id = r.course_id where r.student_id = ? ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, studentId);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<String> allCourses = new ArrayList<>();

			while (resultSet.next()) {
				allCourses.add(resultSet.getString("course_name"));
			}
			return allCourses;
		}
	}

}
