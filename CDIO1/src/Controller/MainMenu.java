package Controller;

import java.util.List;

import data.Data;
import data.DALException;
import data.OperatoerDTO;
import data.Operator;
import Boundary.Boundary;

public class MainMenu {
	String password, oprID;
	boolean choice = true; 
	boolean choice1 = false; 
	
	Boundary boundary = new Boundary();
	Data data = new Data();

	public MainMenu() {
	}

	public void RunMain() throws DALException {
		
		Operator flemming = new Operator("Flemming", "FH", "367495-7483","Flemse");
		OperatoerDTO Operatoer1 = new OperatoerDTO(14, flemming);
		data.createOperatoer(Operatoer1);
		List<OperatoerDTO> operatorlist = data.getOperatoerList();
		
		
		
		// asks the user if he/she wants to try to login again if it failed
		while(choice==true){
			String[] login = boundary.login();
			oprID=login[0]; login[1]=password; 
			int opr_ID= Integer.parseInt(oprID);
			if(data.getOperatoer(opr_ID).getPassword().equalsIgnoreCase(password)){
				//here the mainMenu that acctually runs, after the login has completed, if the user decides to logout the login loop will run again. 
				choice1=true; 
				while(choice1==true){
					 boundary.showStringMessage("You succesfully logged in");
					choice=false;
					
					
				}
				
				}
			else{
				boundary.showStringMessage("your login didn't match anyone in our current list of Operators");
				
			}
			
		}
		
		
		
		
	}



}
