package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.model.Course;

public class CoursesDAO {

	public int countTotalCourses(Connection connection) throws SQLException {
		String sqlQuery = "select count(*) from courses";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next())
				return resultSet.getInt(1);
		}
		return 0;
	}

	public List<Course> getAllCourses(Connection connection) throws SQLException {
		List<Course> courses = new ArrayList<>();
		String sqlQuery = "select * from courses order by course_id ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Course course = new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"),
						resultSet.getString("duration"), resultSet.getDouble("fees"),
						resultSet.getString("trainer_name"));
				courses.add(course);
			}
		}
		return courses;
	}

	public Course getCourseById(Connection connection, int id) throws SQLException {
		String sqlQuery = "select * from courses where course_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"),
							resultSet.getString("duration"), resultSet.getDouble("fees"),
							resultSet.getString("trainer_name"));
				}
			}
		}
		return null;
	}

	public int addCourse(Connection connection, Course course) throws SQLException {
		String sqlQuery = "insert into courses (course_name, duration, fees, trainer_name) values (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setString(1, course.getCourseName());
			preparedStatement.setString(2, course.getDuration());
			preparedStatement.setDouble(3, course.getFees());
			preparedStatement.setString(4, course.getTrainerName());
			return preparedStatement.executeUpdate();
		}
	}

	public int updateCourse(Connection connection, Course course) throws SQLException {
		String sqlQuery = "update courses set course_name=?, duration=?, fees=?, trainer_name=? where course_id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setString(1, course.getCourseName());
			preparedStatement.setString(2, course.getDuration());
			preparedStatement.setDouble(3, course.getFees());
			preparedStatement.setString(4, course.getTrainerName());
			preparedStatement.setInt(5, course.getCourseId());
			return preparedStatement.executeUpdate();
		}
	}

	public int deleteCourse(Connection connection, int id) throws SQLException {
		String sqlQuery = "delete from courses where course_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		}
	}

	public int getRegistrationCountByCourse(Connection connection, int courseId, String status) throws SQLException {
		String sqlQuery = "select count(*) from registrations where course_id = ? and status = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setInt(1, courseId);
			preparedStatement.setString(2, status);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					return resultSet.getInt(1);
			}
		}
		return 0;
	}

	public boolean isCourseNameExists(Connection connection, String courseName) throws SQLException {

		String sqlQuery = "select count(*) from courses where lower(course_name) = lower(?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setString(1, courseName.trim());
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1) > 0;
				}
			}
		}
		return false;
	}
}