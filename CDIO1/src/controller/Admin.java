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
				"3. edit user", "4. show alle users" };

		int menu = boundary.menu(options,
				"Welcome sysadmin, what would you like to do? ");

		// here the loop for choosing a menu begins while the user has not
		// decided to quit the menus he will be able to continue using the menu
		while (adminMenu) {
			switch (menu) {

			// case 1 is the menu to create a new user, here the user admin will
			// be prompted to enter the name, initials, cpr, password and oprID
			case 1:

				String oprNavn = boundary
						.getString("Please enter the name of the user you want to create");
				String ini = boundary.getString("please enter the initials");
				String cpr = boundary
						.getString("please enter the cpr number of the user: xxxxxx-xxxx");
				String password = boundary
						.getString("please enter the desired password");
				int oprId = boundary
						.getInt("please enter the desired oprID between 11 and 99");

				// TODO Der er ingen grund til at kontrollere om operator id
				// findes, det ordner data laget.
				try {
					for (int i = 0; i <= data.getOperatoerList().size(); i++) {
						if (data.getOperatoerList().get(i).getOprId() == oprId) {
							oprId = boundary
									.getInt("please enter another oprId that one already exists");
							i = 0;
						}

					}
				} catch (DALException e) {

					e.printStackTrace();
				}

				OperatoerDTO operatorDTO = new OperatoerDTO(oprId, oprNavn,
						ini, cpr, password);
				try {
					data.createOperatoer(operatorDTO);
				} catch (DALException e) {

					e.printStackTrace();
				}

				break;

			case 2:

				// case 2 is the menu for deleting a user, all the users in the
				// memory is displayed to the admin with their corresponding
				// index number, so the number that the admin enters stands for
				// the user that will be removed.
				int deleteChoice;
				try {
					for (int i = 0; i <= data.getOperatoerList().size(); i++) {
						boundary.showStringMessage(i + ":    "
								+ data.getOperatoerList().get(i));
						deleteChoice = boundary
								.getInt("please choose wich user u want to delete by writing the number in front of the user");
						// TODO denne linie vil ikke påvirke den data der er
						// gemt i data laget.
						// hvis vi skal understøtte at man kan slette bugere,
						// skal vi have en ekstra metode i datalaget
						// eller tilpasse updateOperatoer(), så den sletter en
						// bruger hvis man sætter alle værdier til nul f.eks.
						data.getOperatoerList().remove(deleteChoice);
					}

				} catch (DALException e) {

					e.printStackTrace();
				}

				break;

			case 3:
				try {
					for (int i = 0; i <= data.getOperatoerList().size(); i++) {
						boundary.showStringMessage(i + ". "
								+ data.getOperatoerList().get(i));
					}
					int updateChoice = boundary
							.getInt("please enter the number of the user you want to update");
					int opr_id = data.getOperatoerList().get(updateChoice)
							.getOprId();
					data.getOperatoer(opr_id);

				} catch (DALException e) {

					e.printStackTrace();
				}

				break;

			case 4:
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

			default:
				boundary.showStringMessage("you did not choose a valid menu please choose another");
				adminMenu = true;

			}
		}

	}

}
