package net.slipp.db;

import java.util.HashMap;
import java.util.Map;

import net.slipp.user.User;

public class Database {

	private static Map<String, User> users = new HashMap<String, User>();
	
	public static void addUser(User user){
		System.out.println("user : " + user);
		users.put(user.getUserId(), user);
		
	}

	public static User findByUserId(String userId) {
		return users.get(userId);
	}
	
}