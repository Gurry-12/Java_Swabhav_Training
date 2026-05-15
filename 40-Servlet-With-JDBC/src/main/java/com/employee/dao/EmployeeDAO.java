package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employee.model.LeaveRequest;

public class EmployeeDAO {

	public int createEmployeeLeaveData(Connection connection, String employeeName, String employeeId,
			String department, String leaveType, int days, String reason, String approvalMessage) throws SQLException {

		String sqlQuery = "INSERT INTO leave_applications "
				+ "(employee_name, employee_id, department, leave_type, leave_days, reason, approval_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			
			preparedStatement.setString(1, employeeName);
			preparedStatement.setString(2, employeeId);
			preparedStatement.setString(3, department);
			preparedStatement.setString(4, leaveType);
			preparedStatement.setInt(5, days);
			preparedStatement.setString(6, reason);
			preparedStatement.setString(7, approvalMessage);
			
			return preparedStatement.executeUpdate();
		}
	}
	
	
	public List<LeaveRequest> getAllLeaveRequests(Connection connection) throws SQLException {
	    List<LeaveRequest> list = new ArrayList<>();
	    String sqlQuery = "SELECT * FROM leave_applications";
	    
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

	    	ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            list.add(new LeaveRequest(
	            		resultSet.getString("employee_name"),
	            		resultSet.getString("employee_id"),
	            		resultSet.getString("department"),
	            		resultSet.getString("leave_type"),
	            		resultSet.getInt("leave_days"),
	            		resultSet.getString("reason"),
	            		resultSet.getString("approval_status")
	            ));
	        }
	    }
	    return list;
	}


	public LeaveRequest getLeaveById(Connection connection, String employeeId) throws SQLException {
		
		String sqlQuery = "select * from leave_applications where employee_id = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			
			preparedStatement.setString(1, employeeId);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
	                // Map database columns to your LeaveRequest Model
	                return new LeaveRequest(
	                    resultSet.getString("employee_name"),
	                    resultSet.getString("employee_id"),
	                    resultSet.getString("department"),
	                    resultSet.getString("leave_type"),
	                    resultSet.getInt("leave_days"),
	                    resultSet.getString("reason"),
	                    resultSet.getString("approval_status") // or "status" depending on your DB column name
	                );
	            }
			}
			return null;
			
			
		}
	}
}
