package com.project.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.app.model.Registration;

public class RegistrationDAO {

	public int insertRegistration(Connection connection, Registration registration) throws SQLException {

		String sqlQuery = "insert into registration ( student_id, course_id, fees_paid) values ( ? , ? , ? );";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, registration.getStudentId());
			preparedStatement.setInt(2, registration.getCourseId());
			preparedStatement.setDouble(3, registration.getFeesPaid());

			return preparedStatement.executeUpdate();
		}
	}

	public boolean isAlreadyRegistered(Connection connection, int studentId, int courseId) throws SQLException {

		String sqlQuery = "select * from registration where student_id = ? and course_id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}
			return true;
		}
	}

	// Get all registrations for a student
	public List<String[]> selectByStudentId(Connection connection, int studentId) throws SQLException {

		String sqlQuery = "select s.id, s.name, s.age, d.department_name,"
				+ " r.reg_id, coalesce(c.course_name, 'N/A') as course_name , "
				+ "coalesce(r.fees_paid, 0) as fees_paid " + "from student as s "
				+ "left join department d on s.department_id = d.id "
				+ "left join registration as r on s.id = r.student_id "
				+ "left join course as c on r.course_id = c.id where s.id = ?; ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, studentId);

			ResultSet resultSet = preparedStatement.executeQuery();

			List<String[]> rows = new ArrayList<>();

			while (resultSet.next()) {
				rows.add(new String[] { 
						String.valueOf(resultSet.getInt("id")),
						resultSet.getString("name"),
						String.valueOf(resultSet.getInt("age")), 
						resultSet.getString("department_name"),
						resultSet.getString("course_name"), 
						String.valueOf(resultSet.getDouble("fees_paid")) });
			}
			return rows;
		}
	}

	// Update fee for a specific course
	public int updateCourseFee(Connection connection, int studentId, int courseId, double newFee) throws SQLException {

		String sqlQuery = "update registration set fees_paid = ? where student_id = ? and course_id = ? ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setDouble(1, newFee);
			preparedStatement.setInt(2, studentId);
			preparedStatement.setInt(3, courseId);

			return preparedStatement.executeUpdate();
		}
	}

	// Cancel one registration
	public int deleteRegistration(Connection connection, int studentId, int courseId) throws SQLException {

		String sqlQuery = "delete from registration where student_id = ? and course_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);
			return preparedStatement.executeUpdate();
		}
	}

	// Delete ALL registrations of a student (for delete-student transaction)
	public int deleteAllRegistrationByStudentId(Connection connection, int studentId) throws SQLException {
		String sqlQuery = "delete from registration where student_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

			preparedStatement.setInt(1, studentId);
			return preparedStatement.executeUpdate();
		}
	}

	//  High paying students report
	public List<String[]> getHighPayingStudents(Connection connection, double minFee) throws SQLException {

		String sqlQuery = "select s.id, s.name, s.age , d.department_name , "
				+ "count(r.course_id) course_count, sum(fees_paid) total_fees "
				+ "from student s " 
				+ "left join department d on s.department_id = d.id "
				+ "join registration r "
				+ "on s.id = r.student_id " 
				+ "group by s.id, s.name, s.age, d.department_name "
				+ "having sum(fees_paid) > ? ";

		List<String[]> list = new ArrayList<>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setDouble(1, minFee);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(new String[] { 
						String.valueOf(resultSet.getInt("id")), 
						resultSet.getString("name"),
						String.valueOf(resultSet.getInt("age")), 
						resultSet.getString("department_name"),
						String.valueOf(resultSet.getInt("course_count")), 
						String.valueOf(resultSet.getDouble("total_fees")) 
						});
			}
		}
		return list;
	}

	// REPORT: COURSE-WISE STUDENT COUNT
	public List<String[]> getCourseWiseCount(Connection connection) throws SQLException {

		String sql = "select c.course_name, count(r.student_id) as student_count " 
					+ "from registration r "
					+ "join course c on r.course_id = c.id " 
					+ "group by c.course_name order by student_count desc";

		List<String[]> list = new ArrayList<>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(new String[] { 
						resultSet.getString("course_name"), 
						String.valueOf(resultSet.getInt("student_count")) 
						});
			}
		}
		return list;
	}
}
