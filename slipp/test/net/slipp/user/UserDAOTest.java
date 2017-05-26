package net.slipp.user;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private UserDAO userDao;
	
	@Before
	public void setup() {

		userDao = new UserDAO();

	}
	
	@Test
	public void connection() throws SQLException {
		
		UserDAO userDao = new UserDAO();
		Connection con = userDao.getConnection();
		assertNotNull(con);
	}
	
	/*
	@Test
	public void addUser() throws Exception{
		User user = UserTest.TEST_USER;
		//userDao.addUser(UserTest.TEST_USER);
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
		
		//userDao.addUser(UserTest.TEST_USER);
		
	}*/
	
	
	@Test
	public void crud() throws Exception{
		
		User user = UserTest.TEST_USER;

		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
		
		User dbUser = userDao.findByUserId(user.getUserId());
		assertEquals(user, dbUser);
		//userDao.addUser(UserTest.TEST_USER);
		
		
		User updateUser = new User(user.getUserId(), "updatePass", "updateName", "updateEmail@");
		userDao.updateUser(updateUser);
		dbUser = userDao.findByUserId(updateUser.getUserId());
		assertEquals(updateUser, dbUser);
		
	}
	
	/*
	public void findByUserId() throws Exception {

		User user = userDao.findByUserId("userId");
		assertEquals(UserTest.TEST_USER, user);
		
	}*/
	
	
	private void assertFalse(User user, User dbUser) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void unfind_user_check() throws Exception{

		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		User dbUser = userDao.findByUserId(user.getUserId());
		assertNull(dbUser);
	}
	
	
	
}
