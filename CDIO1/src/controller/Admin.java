package controller;

import boundary.IBoundary;
import data.*;

public class Admin implements ISubController {
	IBoundary boundary;

	public Admin(IBoundary bound) {
		boundary = bound;
	}

	public void run(int userId) {
		Data data = new Data();
		boolean adminMenu = true;
		String[] options = { "1. create user ", "2. delete user",
				"3. edit user", "4. show alle users" };

		int menu = boundary.menu(options,
				"Welcome sysadmin, what would you like to do? ");
		while (adminMenu) {
			switch (menu) {
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

				Operator operator = new Operator(oprNavn, ini, cpr, password);
				OperatoerDTO operatorDTO = new OperatoerDTO(oprId, operator);
				try {
					data.createOperatoer(operatorDTO);
				} catch (DALException e) {

					e.printStackTrace();
				}

				break;

			case 2:
				int deleteChoice;
				try {
					for (int i = 0; i <= data.getOperatoerList().size(); i++) {
						boundary.showStringMessage(i + ":    "
								+ data.getOperatoerList().get(i));
						deleteChoice = boundary
								.getInt("please choose wich user u want to delete by writing the number in front of the user");
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
					int opr_id=data.getOperatoerList().get(updateChoice).getOprId();
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
