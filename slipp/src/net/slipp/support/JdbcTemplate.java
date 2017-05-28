package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.slipp.user.User;
import net.slipp.user.UserDAO;

public abstract class JdbcTemplate {
	
	public Connection getConnection() throws SQLException {

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

	public void executeUpdate(User user) throws SQLException {
				
		String sql = createQuery();
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			setParameters(user, pstmt);
			
			pstmt.executeUpdate();
			
		} finally{
			
			if(pstmt != null){
				pstmt.close();
			}
			
			if(conn != null){
				conn.close();
			}
		}
	}
	
	public abstract String createQuery();
	public abstract void setParameters(User user, PreparedStatement pstmt)throws SQLException;
	
}
