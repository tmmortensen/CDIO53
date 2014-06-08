package admin.controller;

import admin.data.IUserReadOnly;
import admin.data.UserDTO;

public class UserSession {
	private int userId;
	private String password;
	private IUserReadOnly data;
	
	public UserSession(){
		
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
		return true;
		//TODO restore this when data layer works
		/*try{
			UserDTO operator = data.getOperatoer(userId);
			if (operator.getPassword().equals(password))
				return true;
		} catch (Exception e){}
		return false;*/
	}
	
	public boolean isAdmin(){
		return accessLevel() <= 0;
	}
	
	public boolean isPharmacist(){
		return accessLevel() <= 1;
	}
	
	public boolean isForeman(){
		return accessLevel() <= 2;
	}
	
	
	public int accessLevel(){
		return 0;
		//TODO remake this when data layer works
	}
	
	public int getId(){
		return userId;
	}
}
