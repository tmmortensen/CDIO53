package controller;

import data.IDataReadOnly;
import data.OperatoerDTO;

public class User {
	private int userId;
	private String password;
	private IDataReadOnly data;
	
	public User(){
		
	}
	
	public void init(IDataReadOnly data){
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
			OperatoerDTO operator = data.getOperatoer(userId);
			if (operator.getPassword().equals(password))
				return true;
		} catch (Exception e){}
		return false;
	}
	
	public boolean isAdmin(){
		try {
			OperatoerDTO operator = data.getOperatoer(userId);
			return operator.isAdmin();
		} catch (Exception e) {}
		return false;
	}
	
	public int getId(){
		return userId;
	}
}
