package controller;

import data.IOperatoerDAO;
import data.OperatoerDTO;
import boundary.Boundary;
import boundary.IBoundary;

public class Password implements ISubController {
	IBoundary boundary;
	IOperatoerDAO opData;
	public Password(IBoundary bound, IOperatoerDAO data){
		boundary = bound;
		opData = data;
	}
	public void run(int userId){
		OperatoerDTO operator = opData.getOperatoer(userId);
		String[] login = boundary.login();
		int id;
		boolean done = false; 
		do {
			try{
				id = Integer.parseInt(login[0]);
			}
			catch(NumberFormatException e){
				//boundary.showStringMessage("Ugyldigt ID");
				id = 0;	
			} 
			if(id != userId || login[1] != operator.getPassword()){
				String cancel = boundary.getString("Login information does not match. \n Enter \"cancel\" to return to main menu or press enter to continue.");
				if (cancel.equalsIgnoreCase("cancel"))
					return;
			} else
				done = true;
			
		}	while (!done);
		String newPass1 = boundary.getString("Enter new password:");
		String newPass2 = boundary.getString("Repeat new password:");
		
		if (newPass1 == newPass2 ){
			
		}
	}
	
}
