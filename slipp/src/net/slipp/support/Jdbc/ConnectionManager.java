package net.slipp.support.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static Connection getConnection() throws SQLException {

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "board";
		String pw = "board";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}
}
