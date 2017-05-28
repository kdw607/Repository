package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.slipp.user.User;
import net.slipp.user.UserDAO;

public abstract class JdbcTemplate {

	public void executeUpdate(String sql) throws SQLException {
				
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn = ConnectionManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt);
			
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
	
	public abstract void setParameters(PreparedStatement pstmt)throws SQLException;
	
}
