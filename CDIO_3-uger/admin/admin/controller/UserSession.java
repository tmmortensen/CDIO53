package admin.controller;

import admin.data.IUsersReadOnly;
import admin.data.UserDTO;
import admin.data.UserType;

public class UserSession {
	private int userId;
	private String password;
	private IUsersReadOnly data;
	
	public void init(IUsersReadOnly data){
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
			UserDTO user = data.getUser(userId);
			if (user.getPassword().equals(password))
				return true;
		} catch (Exception e){}
		return false;
	}
	
	public boolean isAdmin(){
		return accessLevel() <= UserType.ADMIN.ordinal();
	}
	
	public boolean isPharmacist(){
		return accessLevel() <= UserType.PHARMACIST.ordinal();
	}
	
	public boolean isForeman(){
		return accessLevel() <= UserType.FOREMAN.ordinal();
	}
	
	
	public int accessLevel(){
		try{
			UserDTO user = data.getUser(userId);
			return user.getAccesLevel();
		} catch (Exception e){}
		return Integer.MAX_VALUE;
	}
	
	public int getId(){
		return userId;
	}
}
