package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.model.Student;

public class StudentsDAO {

	public int countTotalStudents(Connection connection) throws SQLException {
		String sqlQuery = "select count(*) from students";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}

	public List<Student> getAllStudents(Connection connection) throws SQLException {
		List<Student> students = new ArrayList<>();
		String sqlQuery = "select * from students order by student_id";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Student student = new Student(rs.getInt("student_id"), rs.getString("student_name"),
						rs.getString("email"), rs.getString("phone"), rs.getInt("age"), rs.getString("city"));
				students.add(student);
			}
		}
		return students;
	}

	public Student getStudentById(Connection connection, int id) throws SQLException {
		String sqlQuery = "select * from students where student_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Student(rs.getInt("student_id"), rs.getString("student_name"), rs.getString("email"),
							rs.getString("phone"), rs.getInt("age"), rs.getString("city"));
				}
			}
		}
		return null;
	}

	public int addStudent(Connection connection, Student student) throws SQLException {
		String sqlQuery = "insert into students (student_name, email, phone, age, city) values (?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getPhone());
			ps.setInt(4, student.getAge());
			ps.setString(5, student.getCity());
			return ps.executeUpdate();
		}
	}

	public int updateStudent(Connection connection, Student student) throws SQLException {
		String sqlQuery = "update students set student_name=?, email=?, phone=?, age=?, city=? where student_id=?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getPhone());
			ps.setInt(4, student.getAge());
			ps.setString(5, student.getCity());
			ps.setInt(6, student.getStudentId());
			return ps.executeUpdate();
		}
	}

	public int deleteStudent(Connection connection, int id) throws SQLException {
		String sqlQuery = "delete from students where student_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, id);
			return ps.executeUpdate();
		}
	}

	public int getRegistrationCountByStudent(Connection connection, int studentId) throws SQLException {
		String sqlQuery = "select count(*) from registrations where student_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setInt(1, studentId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					return resultSet.getInt(1);
			}
		}
		return 0;
	}

	public boolean varifyDuplicateStudent(Connection connection, String email, String phone) throws SQLException {

		String sqlQury = "select * from students where email = ? and phone = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQury)) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, phone);

			ResultSet resultSet = preparedStatement.executeQuery();

			return resultSet.next();
		}
	}
}