package net.slipp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.user.User;
import net.slipp.user.UserDAO;

public class JdbcTemplate {

	public void executeUpdate(String sql, PreparedStatementSetter pss) throws SQLException {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pss.setParameters(pstmt);
			
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
	
	public <T> T executeQuery(String sql, PreparedStatementSetter pss, RowMapper<T> rm)
			throws SQLException{
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pss.setParameters(pstmt);	
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()){
				return null;
			}
			
			return rm.mapRow(rs);
		}finally{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}	
			if(conn != null){
				conn.close();
			}
		}
	}
}
