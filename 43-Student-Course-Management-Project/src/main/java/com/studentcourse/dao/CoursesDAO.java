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
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {
			if (rs.next())
				return rs.getInt(1);
		}
		return 0;
	}

	public List<Course> getAllCourses(Connection connection) throws SQLException {
		List<Course> courses = new ArrayList<>();
		String sqlQuery = "select * from courses order by course_id ";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Course course = new Course(rs.getInt("course_id"), rs.getString("course_name"),
						rs.getString("duration"), rs.getDouble("fees"), rs.getString("trainer_name"));
				courses.add(course);
			}
		}
		return courses;
	}

	public Course getCourseById(Connection connection, int id) throws SQLException {
		String sqlQuery = "select * from courses where course_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Course(rs.getInt("course_id"), rs.getString("course_name"), rs.getString("duration"),
							rs.getDouble("fees"), rs.getString("trainer_name"));
				}
			}
		}
		return null;
	}

	public int addCourse(Connection connection, Course course) throws SQLException {
		String sqlQuery = "insert into courses (course_name, duration, fees, trainer_name) values (?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getDuration());
			ps.setDouble(3, course.getFees());
			ps.setString(4, course.getTrainerName());
			return ps.executeUpdate();
		}
	}

	public int updateCourse(Connection connection, Course course) throws SQLException {
		String sqlQuery = "update courses set course_name=?, duration=?, fees=?, trainer_name=? where course_id=?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getDuration());
			ps.setDouble(3, course.getFees());
			ps.setString(4, course.getTrainerName());
			ps.setInt(5, course.getCourseId());
			return ps.executeUpdate();
		}
	}

	public int deleteCourse(Connection connection, int id) throws SQLException {
		String sqlQuery = "delete from courses where course_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, id);
			return ps.executeUpdate();
		}
	}

	public int getRegistrationCountByCourse(Connection connection, int courseId) throws SQLException {
		String sqlQuery = "select count(*) from registrations where course_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, courseId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		}
		return 0;
	}
}