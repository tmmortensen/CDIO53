package admin.controller;

import admin.data.IUserReadOnly;
import admin.data.UserDTO;

public class User {
	private int userId;
	private String password;
	private IUserReadOnly data;
	
	public User(){
		
	}
	
	public void init(IUserReadOnly data){
		this.data = data;
	}
	
	public boolean isinitialized(){
		return data != null;
	}
	
	public boolean login(int id, String password){
		this.userId = id;
		this.password = password;
		return isLoggedIn();
	}
	
	public void logout(){
		this.userId = 0;
		this.password = "";
	}
	
	public boolean isLoggedIn(){
		try{
			UserDTO operator = data.getOperatoer(userId);
			if (operator.getPassword().equals(password))
				return true;
		} catch (Exception e){}
		return false;
	}
	
	public boolean isAdmin(){
		try {
			UserDTO operator = data.getOperatoer(userId);
			return operator.isAdmin();
		} catch (Exception e) {}
		return false;
	}
	
	public int getId(){
		return userId;
	}
}
