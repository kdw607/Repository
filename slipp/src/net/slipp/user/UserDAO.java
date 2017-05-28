package net.slipp.user;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.JdbcTemplate;

public class UserDAO {

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

	public void addUser(User user) throws SQLException {
		
		JdbcTemplate jdbc = new JdbcTemplate(){

			@Override
			public void setParameters(PreparedStatement pstmt)
					throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
			
		};
		
		String sql = "insert into users values(?, ?, ?, ?)";
		jdbc.executeUpdate(sql);
	}


	public User findByUserId(String userId) throws SQLException{

		String sql = "select * from users where userId = ?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);	
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				User user = new User(rs.getString("userId"),
									 rs.getString("password"),
									 rs.getString("name"),
									 rs.getString("email"));
				return user;
			}

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
		return null;
	}

	public void removeUser(String userId) throws SQLException {

		JdbcTemplate jdbc = new JdbcTemplate() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,  userId);
			}
		};
		
		String sql = "delete from users where userId = ?";
		jdbc.executeUpdate(sql);
	}

	public void updateUser(User user) throws SQLException {
		
		JdbcTemplate jdbc = new JdbcTemplate() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}
		};
		
		String sql = "update users set password=?, name=?, email=? where userId=?";
		jdbc.executeUpdate(sql);
	}
}