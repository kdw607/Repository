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
	
	public void executeUpdate(String sql, Object... parameters) throws SQLException {
		
		
		PreparedStatementSetter pss = createPreparedstatementSetter(parameters);
		executeUpdate(sql, pss);
		
	}
	
	public <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss)
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
	
	
	public <T> T executeQuery(String sql, RowMapper<T> rm,  Object... parameters)
			throws SQLException{
		
		PreparedStatementSetter pss = createPreparedstatementSetter(parameters);
		return executeQuery(sql, rm, pss);
	}

	
	
	
	private PreparedStatementSetter createPreparedstatementSetter(Object... parameters) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				for (int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
			}
		};
	}
}