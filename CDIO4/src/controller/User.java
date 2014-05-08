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
	
	public boolean checkId(int id){
		this.userId = id;
		return getUserId();
	}
	
	public boolean checkPassword(String password){
		this.password = password;
		return getUserId();
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
	
	public boolean getUserId(){
			try {
				OperatoerDTO operator = data.getOperatoer(userId);
				if(operator.getPassword().equals(password));
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public String getUserPassword(){
		try {
			OperatoerDTO operator = data.getOperatoer(userId);
			if (operator.getPassword().equals(password))
				return this.password;
		} catch (Exception e) {
			
		}
		return password;
	}
	
	public boolean isAdmin(){
		try {
			OperatoerDTO operator = data.getOperatoer(userId);
			return operator.isAdmin();
		} catch (Exception e) {}
		return false;
	}
}
