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
		
		JdbcTemplate jdbc = new JdbcTemplate();
		
		String sql = "insert into users values(?, ?, ?, ?)";
		jdbc.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}


	public User findByUserId(String userId) throws SQLException{
		
		RowMapper<User> rm = new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getString("email"));
			}
		};
		JdbcTemplate jdbc = new JdbcTemplate();

		String sql = "select * from users where userId = ?";
		return jdbc.executeQuery(sql, rm, userId);

	}

	public void removeUser(String userId) throws SQLException {

		JdbcTemplate jdbc = new JdbcTemplate();
		
		String sql = "delete from users where userId = ?";
		jdbc.executeUpdate(sql, userId);
	}

	public void updateUser(User user) throws SQLException {

		JdbcTemplate jdbc = new JdbcTemplate();
		
		String sql = "update users set password=?, name=?, email=? where userId=?";
		jdbc.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
}