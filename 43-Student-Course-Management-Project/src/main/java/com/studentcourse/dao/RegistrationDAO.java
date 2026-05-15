package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.model.Registration;
import com.studentcourse.model.RegistrationDetails;

public class RegistrationDAO {

	public int countTotalRegistrations(Connection connection) throws SQLException {
		String sqlQuery = "select count(*) from registrations";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {
			if (rs.next())
				return rs.getInt(1);
		}
		return 0;
	}

	public List<RegistrationDetails> getAllRegistrations(Connection connection) throws SQLException {
		List<RegistrationDetails> list = new ArrayList<>();
		String sqlQuery = "select r.*, s.student_name, c.course_name " + "from registrations r "
				+ "join students s on r.student_id = s.student_id " + "join courses c on r.course_id = c.course_id "
				+ "order by r.registration_id ";

		try (PreparedStatement ps = connection.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				RegistrationDetails reg = new RegistrationDetails(rs.getInt("registration_id"), rs.getInt("student_id"),
						rs.getInt("course_id"), rs.getDate("registration_date").toLocalDate(), rs.getString("status"),
						rs.getString("student_name"), rs.getString("course_name"));
				list.add(reg);
			}
		}
		return list;
	}

	public int addRegistration(Connection connection, Registration reg) throws SQLException {
		String sqlQuery = "insert into registrations (student_id, course_id, registration_date, status) values (?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, reg.getStudentId());
			ps.setInt(2, reg.getCourseId());
			ps.setDate(3, Date.valueOf(reg.getRegistrationDate()));
			ps.setString(4, reg.getStatus());
			return ps.executeUpdate();
		}
	}

	public int updateRegistrationStatus(Connection connection, int id, String status) throws SQLException {
		String sqlQuery = "update registrations set status = ? where registration_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, status);
			ps.setInt(2, id);
			return ps.executeUpdate();
		}
	}

	public int deleteRegistration(Connection connection, int id) throws SQLException {
		String sqlQuery = "delete from registrations where registration_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setInt(1, id);
			return ps.executeUpdate();
		}
	}

	public boolean checkExistRegitration(Connection conncetion, int studentId, int courseId) throws SQLException {

		String sqlQuery = "select * from registrations where student_id = ? and course_id = ?";
		try (PreparedStatement preparedStatement = conncetion.prepareStatement(sqlQuery)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}

			return false;
		}
	}
}