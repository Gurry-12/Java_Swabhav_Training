package com.studentcourse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/student_course_db";
	private static final String USER = "root";
	private static final String PASSWORD = "Guriii123";

	private DBConnection() {
	}

	// DB connection
	public static Connection getConnection() throws SQLException {
		try {
            // Register the driver manually
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

