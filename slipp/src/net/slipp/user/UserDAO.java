package net.slipp.user;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.JdbcTemplate;
import net.slipp.support.PreparedStatementSetter;
import net.slipp.support.RowMapper;
import net.slipp.support.bark_SelectJdbcTemplate;

public class UserDAO {

	public void addUser(User user) throws SQLException {
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());				
			}
		};
		JdbcTemplate jdbc = new JdbcTemplate();
		
		String sql = "insert into users values(?, ?, ?, ?)";
		jdbc.executeUpdate(sql, pss);
	}


	public User findByUserId(String userId) throws SQLException{
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		
		RowMapper rm = new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getString("email"));
			}
		};
		JdbcTemplate jdbc = new JdbcTemplate();

		String sql = "select * from users where userId = ?";
		return (User)jdbc.executeQuery(sql, pss, rm);

	}

	public void removeUser(String userId) throws SQLException {

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);				
			}
		};
		JdbcTemplate jdbc = new JdbcTemplate();
		
		String sql = "delete from users where userId = ?";
		jdbc.executeUpdate(sql, pss);
	}

	public void updateUser(User user) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());				
			}
		};
		JdbcTemplate jdbc = new JdbcTemplate();
		
		String sql = "update users set password=?, name=?, email=? where userId=?";
		jdbc.executeUpdate(sql, pss);
	}
}