package com.project.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.app.model.Student;

public class StudentDAO {

	// insert
	public int insertStudent(Connection connection, Student student) throws SQLException {

		String sqlQuery = "insert into student (id, name, age, department_id) values ( ? , ? , ? , ?); ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setInt(4, student.getDepartmentId());

			return preparedStatement.executeUpdate();
		}
	}

	// select by id
	public Student selectStudentWithId(Connection connection, int id) throws SQLException {

		String sqlQuery = "select * from student where id = ? ;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				return new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
						resultSet.getInt("department_id"));
			}
			return null;
		}
	}

	// exist test
	public boolean existsById(Connection connection, int id) throws SQLException {

		String sqlQuery = "select * from student where id = ? ;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}
			return true;
		}
	}

	// For View All (JOIN query)
	public List<String[]> selectAllWithRegistrations(Connection connection) throws SQLException {

		String sqlQuery = "select s.id, s.name, s.age, d.department_name,"
				+ " r.reg_id, coalesce(c.course_name, 'N/A') as course_name , "
				+ "coalesce(r.fees_paid, 0) as fees_paid " + "from student as s "
				+ "left join department d on s.department_id = d.id "
				+ "left join registration as r on s.id = r.student_id "
				+ "left join course as c on r.course_id = c.id; ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			ResultSet resultSet = preparedStatement.executeQuery();
			List<String[]> rows = new ArrayList<>();

			while (resultSet.next()) {
				rows.add(new String[] { String.valueOf(resultSet.getInt("id")), resultSet.getString("name"),
						String.valueOf(resultSet.getInt("age")), resultSet.getString("department_name"),
						resultSet.getString("course_name"), String.valueOf(resultSet.getDouble("fees_paid")) });
			}
			return rows;
		}
	}

	// For Update
	public int updateStudent(Connection connection, int id, String name, int departmentId) throws SQLException {

		String sqlQuery = "update student set name = ? , department_id = ? where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, departmentId);
			preparedStatement.setInt(3, id);

			return preparedStatement.executeUpdate();
		}
	}

	// For Delete (used in transaction)
	public int deleteStudent(Connection connection, int id) throws SQLException {

		String sqlQuery = "delete from student where id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		}

	}
}
