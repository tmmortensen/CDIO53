package controller;

import boundary.IBoundary;
import data.*;

public class Admin implements ISubController {
	IBoundary boundary;
	IOperatoerDAO data;

	public Admin(IBoundary bound, IOperatoerDAO data) {
		boundary = bound;
		this.data = data;
	}

	public void run(int userId) {

		// The user is prompted to choose which menu he would like to enter. and
		// the menu is set to true to begin with.
		boolean adminMenu = true;
		String[] options = { "1. create user ", "2. delete user",
				"3. edit user", "4. show alle users", "5. Tilbage til hovedmenuen." };

		int menu = boundary.menu(options,
				"Welcome sysadmin, what would you like to do? ");

		// here the loop for choosing a menu begins while the user has not
		// decided to quit the menus he will be able to continue using the menu
		while (adminMenu) {
			switch (menu) {

			// case 1 is the menu to create a new user, here the user admin will
			// be prompted to enter the name, initials, cpr, password and oprID
			case 1: // create user

				String oprNavn = boundary
						.getString("Please enter the name of the user you want to create");
				String ini = boundary.getString("please enter the initials");
				String cpr = boundary
						.getString("please enter the cpr number of the user: xxxxxx-xxxx");
				String password = OperatoerDTO.generatePassword();
						
				int oprId;
				do{
					oprId = boundary.getInt("please enter the desired oprID between 11 and 99");
					try {
						data.createOperatoer(new OperatoerDTO(oprId, oprNavn,ini, cpr, password));
						break;
					} catch (DALException e) {
						boundary.showStringMessage(e.getMessage());
						String choice = boundary.getString("Ønsker du at indtaste et alternativt ID?\n Tryk enter for ja ellers skriv \"cancel\"");
						if(choice.equals("cancel")){
							return;
						}
					}
				} while(true);
				boundary.showStringMessage("The user was succesfully created. The password is: " + password);
				break;

			case 2: // delete user
				// case 2 is the menu for deleting a user, all the users in the
				// memory is displayed to the admin with their corresponding
				// index number, so the number that the admin enters stands for
				// the user that will be removed.
				OperatoerDTO operator;
				do{
				oprId = boundary.getInt("Enter the ID of user you wish to delete.");
				try{
					operator = data.getOperatoer(oprId);
					break;
				}
				catch(DALException e){
					String choice = boundary.getString("ID does not exist?\n Press enter to try again or type \"cancel\" to exit.");
					if(choice.equals("cancel")){
						return;
					}
				}
				}while(true);
				boundary.showStringMessage("Er du sikker på at du vil slette brugeren? J/N");
				boundary.showStringMessage("ID:" + operator.getOprId());
				boundary.showStringMessage(operator.getOprNavn());
				boundary.showStringMessage(operator.getIni());
				String choice = boundary.getString(operator.getCpr());
				if(!choice.equalsIgnoreCase("J"))
					break;
				try{
					data.deleteOperatoer(oprId);
					
				}
				catch(DALException e){
					boundary.showStringMessage("The user ID no longer exist");
					return;
				}
				break;

			case 3: // edit user
				
				do{
				oprId = boundary.getInt("Enter the ID of user you to edit.");
				try{
					operator = data.getOperatoer(oprId);
					break;
				}
				catch(DALException e){
					choice = boundary.getString("ID does not exist?\n Press enter to try again or type \"cancel\" to exit.");
					if(choice.equals("cancel")){
						return;
					}
				}
				}while(true);
				String[] optionsList = {
						"1 name",
						"2 initials",
						"3 Cpr-number",
						"4 User ID"
				};
				int optionChoice = boundary.menu(optionsList, "Select the attribute you wish to edit.");
				switch(optionChoice){
				case 1:
					String newName = boundary.getString("Enter the new operator name");
					operator.setOprNavn(newName);
					break;
				case 2:
					String newIni = boundary.getString("Enter the new operator initials");
					operator.setOprNavn(newIni);
					break;
				case 3:
					String newCpr = boundary.getString("Enter the new operator Cpr-number");
					operator.setOprNavn(newCpr);
					break;
				case 4:
					do{
					int newId = boundary.getInt("Enter the new operator user ID");
					int oldId = operator.getOprId();
					operator.setOprId(newId);

					try{
						data.createOperatoer(operator);
						try{
							data.deleteOperatoer(oldId);
							break;
						}
						catch(DALException e){
							boundary.showStringMessage("The user with given ID does not exist anymore");
							return;
						}
					}
					catch(DALException e){
						boundary.showStringMessage(e.getMessage());
						choice = boundary.getString("Do you wish to enter another ID?\n Press enter for yes or type \"cancel\" to exit.");
						if(choice.equals("cancel")){
							return;					
						}
					}
					}while(true);
					break;
				}
			
				break;

			case 4: //list users
				try {
					for (int i = 0; i <= data.getOperatoerList().size(); i++) {
						OperatoerDTO operatoerDTO = data.getOperatoerList()
								.get(i);
						boundary.showStringMessage("name: "
								+ operatoerDTO.getOprNavn());
						boundary.showStringMessage("initials: "
								+ operatoerDTO.getIni());
						boundary.showStringMessage("cpr-number: "
								+ operatoerDTO.getCpr());
						boundary.showStringMessage("password: "
								+ operatoerDTO.getPassword());
					}
				} catch (DALException e) {

					e.printStackTrace();
				}

				break;
			
			case 5:
				return;
			default:
				boundary.showStringMessage("you did not choose a valid menu please choose another");
				adminMenu = true;
			
			}
		}

	}

}
