package com.financemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/PhanVanHoa_K22CNT2";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "@Hoa2004";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("Không tìm thấy Driver MySQL", e);
		}
		return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
	}
}
