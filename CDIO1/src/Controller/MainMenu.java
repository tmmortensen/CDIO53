package Controller;

import data.IOperatoerDAO;
import data.OperatoerDTO;
import Boundary.Boundary;

public class MainMenu {
	String oprID,password,name;
	Boundary boundary = new Boundary();
	OperatoerDTO operatoerDTO = new OperatoerDTO();
	
	public MainMenu(){
	}

	public void RunMain(){
		
		boundary.login(); 
		Operatoer a = operatoerDTO.getOperatoer(oprID);
		oprID = Operatoer.getID;
		
		
		
		
		
		
		
		
		
	}
	public String[] getLogin(){
		String[] user = new String[3];
		user[0]=oprID;
		user[1]=password;
		user[2]=name; 
		return user; 
		
	}
	
}
