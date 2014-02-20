package Controller;

import Boundary.Boundary;

public class MainMenu {
	String oprID,password,name;
	Boundary boundary = new Boundary();
	
	public MainMenu(){
	}

	public void RunMain(){
		
		boundary.login(oprID, password); 
		
		
		
		
		
		
		
		
		
		
	}
	public String[] getLogin(){
		String[] user = new String[3];
		user[0]=oprID;
		user[1]=password;
		user[2]=name; 
		return user; 
		
	}
	
}
