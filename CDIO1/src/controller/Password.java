package controller;

import data.DALException;
import data.IOperatoerDAO;
import data.OperatoerDTO;
import boundary.IBoundary;

public class Password implements ISubController {
	IBoundary boundary;
	IOperatoerDAO opData;
	public Password(IBoundary bound, IOperatoerDAO data){
		boundary = bound;
		opData = data;
	}
	public void run(int userId){
		OperatoerDTO operator;
		try{
			operator = opData.getOperatoer(userId);
		}catch(DALException e){
			boundary.showStringMessage("Der skete en fejl, bruger ID findes ikke i databasen.");
			return;
		}
		
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
			if(id != userId || !login[1].equals(operator.getPassword())){
				String cancel = boundary.getString("Login information does not match. \n Enter \"cancel\" to return to main menu or press enter to continue.");
				if (cancel.equalsIgnoreCase("cancel"))
					return;
			} else
				done = true;
			
		}	while (!done);
		done = false;
		do{
			String newPass1 = boundary.getString("Enter new password:");
			String newPass2 = boundary.getString("Repeat new password:");
		
			if (newPass1.equals(newPass2) && OperatoerDTO.checkPassword(newPass1)){
				operator.setPassword(newPass1);
				try{
					opData.updateOperatoer(operator);
				}
				catch(DALException e){
					boundary.showStringMessage("Der skete en fejl, bruger ID findes ikke i databasen.");
					return;
				}
				done = true;
			}
			else{
				String cancel;
				if (!OperatoerDTO.checkPassword(newPass1))
					cancel = boundary.getString("Password does not meet the requirements. \n Enter \"cancel\" to return to main menu or press enter to try again.");
				else
					cancel = boundary.getString("Password does not match. \n Enter \"cancel\" to return to main menu or press enter to try again.");
				if (cancel.equalsIgnoreCase("cancel"))
					return;
			}
		} while (!done);
	}
	
}
