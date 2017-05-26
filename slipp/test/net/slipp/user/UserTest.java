package net.slipp.user;

import static org.junit.Assert.*;
import net.slipp.db.Database;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	public static User TEST_USER = new User("userId", "password", "name", "javajigi@slipp.net");
	UserDAO userDao;

	
	@Before
	public void setup() throws Exception{
		
		userDao = new UserDAO();
		userDao.removeUser(TEST_USER.getUserId());
	}

	
	@Test
	public void matchPassword(){
		boolean result = TEST_USER.matchPassword("password");
		assertTrue(result);
	}
	
	@Test
	public void notmatchPassword(){
		assertTrue(TEST_USER.matchPassword("password"));
	}
	
	@Test
	public void login() throws Exception{
		
		User user = UserTest.TEST_USER;
		//Database.addUser(user);
		
		userDao.addUser(user);
		
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
		
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception{
		
		User.login("userId2", TEST_USER.getPassword());
		
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception{
		User user = UserTest.TEST_USER;
		//Database.addUser(user);
		//UserDAO userDao = new UserDAO();
		userDao.addUser(user);
		
		User.login(TEST_USER.getUserId(), "password2");
		
	}	
	
}