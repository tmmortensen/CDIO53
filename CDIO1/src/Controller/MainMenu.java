package Controller;

import data.Data;
import data.DALException;
import Boundary.Boundary;

public class MainMenu {
	String password, oprID;
	boolean relogChoice = true;
	boolean mainChoice = false;
	boolean menuChoice = true;
	Password Password = new Password();
	Admin admin = new Admin();
	Test test = new Test();

	Boundary boundary = new Boundary();
	Data data = new Data();

	public MainMenu() {
	}

	public void RunMain() throws DALException {

		// asks the user if he/she wants to try to login again if it failed
		while (relogChoice == true) {
			String[] login = boundary.login();
			oprID = login[0];
			login[1] = password;
			int opr_ID = Integer.parseInt(oprID);
			if (data.getOperatoer(opr_ID).getPassword()
					.equalsIgnoreCase(password)) {

				// here the mainMenu that actually runs, after the login has
				// completed, if the user decides to logout the login loop will
				// run again.
				mainChoice = true;
				while (mainChoice == true) {
					boundary.showStringMessage("You succesfully logged in");
					relogChoice = false;

					// creates the String[] options that holds the options that
					// the user have
					String[] options = {"1. Password ", "2. Test",
							"3. Quit", "" };

					// the admin menu will be shown if you have rights to see
					// it.
					if (opr_ID == 10) {
						options[3] = "4. admin";
					}

					// A loop wrapped around the menu choice, if the user choose
					// a wrong menu or one that does not exist, they will be
					// prompted to try again or simply choose quit
					while (menuChoice == true) {
						int menu = boundary.menu(options, "Welcome "
								+ data.getOperatoer(opr_ID).getOprNavn()
								+ " what would you like to do?");
						switch (menu) {
						case 1:
							Password.run(opr_ID);
							menuChoice = false;
							break;

						case 2:
							test.run(opr_ID);
							menuChoice = false;
							break;

						case 3:
							mainChoice = false;
							menuChoice = false;
							break;

						case 4:
							if (opr_ID == 10) {
								menuChoice = false;
								admin.run(opr_ID);
							} else {
								boundary.showStringMessage("you didn't choose an available menu please choose another");
							}
							break;

						default:
							boundary.showStringMessage("you didn't choose an available menu please choose another");
							menuChoice = false;

						}
					}

				}

			} else {
				boundary.showStringMessage("your login didn't match anyone in our current list of Operators");
				String q = boundary
						.getString("would you like to try again? Y/N ");
				if (q.equalsIgnoreCase("Y")) {
					relogChoice = true;
				} else {
					relogChoice = false;
				}

			}

		}

	}

}
