package net.slipp.user;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.google.gson.annotations.Expose;

import net.slipp.db.Database;

public class User {

	@Expose
	@NotNull
	@Size(min=4, max=12)
	private String userId;
	@Expose(serialize = false)
	@NotNull
	@Size(min=4, max=12)
	private String password;
	@Expose
	@NotNull
	@Size(min=2, max=10)
	private String name;
	@Expose
	@Email
	private String email;
	

	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	
	
	public User() {
	}



	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean matchPassword(String newPassword) {
		
		return this.password.equals(newPassword);
	}
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name="
				+ name + ", email=" + email + "]";
	}
	
	
	public boolean isSameUser(String newuserId){
		if (this.userId == null) {
			return false;
		}
		
		return this.userId.equals(newuserId);
		
	}


	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchException{

		//User user = Database.findByUserId(userId);
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUserId(userId);
		
		if(user == null){
			throw new UserNotFoundException();
		}
		
		
		if(!user.matchPassword(password)){
			throw new PasswordMismatchException();
		}
		
		return true;
	}

}
